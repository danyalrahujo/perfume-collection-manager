package com.example.perfumemanager.view.swing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.perfumemanager.controller.PerfumeManager;
import com.example.perfumemanager.model.Perfume;

@RunWith(GUITestRunner.class)
public class PerfumeSwingViewTest extends AssertJSwingJUnitTestCase {

	private PerfumeSwingView perfumeSwingView;
	private FrameFixture window;
	private PerfumeManager perfumeManager;

	@Override
	protected void onSetUp() {
		perfumeManager = mock(PerfumeManager.class);

		perfumeSwingView = GuiActionRunner.execute(() -> {
			PerfumeSwingView view = new PerfumeSwingView();
			view.setPerfumeManager(perfumeManager);
			return view;
		});

		window = new FrameFixture(robot(), perfumeSwingView);
		window.show();
	}

	@Test
	public void testControlsInitialStates() {
		window.label(JLabelMatcher.withText("id"));
		window.textBox("idTextBox").requireEnabled();

		window.label(JLabelMatcher.withText("name"));
		window.textBox("nameTextBox").requireEnabled();

		window.button(JButtonMatcher.withText("Add")).requireDisabled();

		window.list("perfumeList");

		window.button(JButtonMatcher.withText("Delete Selected")).requireDisabled();
		assertThat(window.button(JButtonMatcher.withText("Delete Selected")).target().isEnabled()).isFalse();

		window.label("errorMessageLabel").requireText(" ");
	}

	@Test
	public void testWhenIdAndNameAreNonEmptyThenAddButtonShouldBeEnabled() {
		window.textBox("idTextBox").enterText("1");
		window.textBox("nameTextBox").enterText("test");

		JButton addButton = window.button(JButtonMatcher.withText("Add")).target();

		assertThat(addButton.isEnabled()).isTrue();
	}

	@Test
	public void testWhenAddButtonIsClickedThenPerfumeManagerShouldAddPerfume() {
		window.textBox("idTextBox").enterText("p001");
		window.textBox("nameTextBox").enterText("Sauvage");
		window.textBox("brandTextBox").enterText("Dior");
		window.textBox("fragrancefamilyTextBox").enterText("Woody");
		window.textBox("volumeTextBox").enterText("100");
		window.textBox("ratingTextBox").enterText("4.5");

		window.button(JButtonMatcher.withText("Add")).click();

		verify(perfumeManager).addPerfume(new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5));
	}

	@Test
	public void testWhenAddButtonIsClickedThenInputFieldsShouldBeCleared() {
		window.textBox("idTextBox").enterText("p001");
		window.textBox("nameTextBox").enterText("Sauvage");
		window.textBox("brandTextBox").enterText("Dior");
		window.textBox("fragrancefamilyTextBox").enterText("Woody");
		window.textBox("volumeTextBox").enterText("100");
		window.textBox("ratingTextBox").enterText("4.5");

		window.button(JButtonMatcher.withText("Add")).click();

		assertThat(window.textBox("idTextBox").target().getText()).isEmpty();
		assertThat(window.textBox("nameTextBox").target().getText()).isEmpty();
		assertThat(window.textBox("brandTextBox").target().getText()).isEmpty();
		assertThat(window.textBox("fragrancefamilyTextBox").target().getText()).isEmpty();
		assertThat(window.textBox("volumeTextBox").target().getText()).isEmpty();
		assertThat(window.textBox("ratingTextBox").target().getText()).isEmpty();
	}

	@Test
	public void testWhenPerfumeIsSelectedThenDeleteButtonShouldBeEnabled() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> perfumeSwingView.showAllPerfumes(java.util.List.of(perfume)));

		window.list("perfumeList").selectItem(0);

		JButton deleteButton = window.button(JButtonMatcher.withText("Delete Selected")).target();

		assertThat(deleteButton.isEnabled()).isTrue();
	}

	@Test
	public void testWhenDeleteButtonIsClickedThenPerfumeManagerShouldRemovePerfume() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(perfume));
		});

		window.list("perfumeList").selectItem(0);

		window.button(JButtonMatcher.withText("Delete Selected")).click();

		verify(perfumeManager).deletePerfume(perfume);

	}

	@Test
	public void testWhenPerfumeIsRemovedThenDeleteButtonShouldBeDisabled() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(perfume));
		});

		window.list("perfumeList").selectItem(0);
		window.button(JButtonMatcher.withText("Delete Selected")).requireEnabled();

		GuiActionRunner.execute(() -> {
			perfumeSwingView.perfumeRemoved(perfume);
		});

		window.button(JButtonMatcher.withText("Delete Selected")).requireDisabled();
	}

	@Test
	public void testWhenPerfumeIsAddedThenItShouldAppearInList() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.perfumeAdded(perfume);
		});

		window.list("perfumeList").requireItemCount(1);

		String displayedItem = window.list("perfumeList").contents()[0];

		assertThat(displayedItem).isEqualTo(perfume.toString());
	}

	@Test
	public void testWhenErrorIsShownThenErrorMessageShouldAppear() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showError("Perfume already exists", perfume);
		});

		window.label("errorMessageLabel").requireText("Perfume already exists");
	}

	@Test
	public void testWhenPerfumeIsSelectedThenItsDetailsShouldAppearInInputFields() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(perfume));
		});

		window.list("perfumeList").selectItem(0);

		window.textBox("idTextBox").requireText("p001");
		window.textBox("nameTextBox").requireText("Sauvage");
		window.textBox("brandTextBox").requireText("Dior");
		window.textBox("fragrancefamilyTextBox").requireText("Woody");
		window.textBox("volumeTextBox").requireText("100");
		window.textBox("ratingTextBox").requireText("4.5");
	}

	@Test
	public void testWhenPerfumeIsSelectedThenUpdateButtonShouldBeEnabled() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(perfume));
		});

		window.list("perfumeList").selectItem(0);

		window.button(JButtonMatcher.withText("Update")).requireEnabled();
	}

	@Test
	public void testWhenUpdateButtonIsClickedThenPerfumeManagerShouldUpdatePerfume() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(perfume));
		});

		window.list("perfumeList").selectItem(0);

		window.textBox("nameTextBox").selectAll().enterText("Sauvage Elixir");
		window.textBox("brandTextBox").selectAll().enterText("Dior");
		window.textBox("fragrancefamilyTextBox").selectAll().enterText("Spicy");
		window.textBox("volumeTextBox").selectAll().enterText("60");
		window.textBox("ratingTextBox").selectAll().enterText("4.8");

		window.button(JButtonMatcher.withText("Update")).click();

		verify(perfumeManager).updatePerfume(new Perfume("p001", "Sauvage Elixir", "Dior", "Spicy", 60, 4.8));
	}

	@Test
	public void testWhenUpdateButtonIsClickedWithoutSelectionThenManagerShouldNotUpdatePerfume() {
		window.button(JButtonMatcher.withText("Update")).click();

		org.mockito.Mockito.verifyNoInteractions(perfumeManager);
	}

	@Test
	public void testWhenDeleteButtonIsClickedWithoutSelectionThenManagerShouldNotDeletePerfume() {
		window.button(JButtonMatcher.withText("Delete Selected")).click();

		org.mockito.Mockito.verifyNoInteractions(perfumeManager);
	}

	@Test
	public void testWhenUpdateButtonIsClickedWithInvalidNumbersThenErrorShouldAppear() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(perfume));
		});

		window.list("perfumeList").selectItem(0);

		window.textBox("volumeTextBox").selectAll().enterText("invalid");
		window.textBox("ratingTextBox").selectAll().enterText("4.8");

		window.button(JButtonMatcher.withText("Update")).click();

		window.label("errorMessageLabel").requireText("Volume and rating must be valid numbers.");

		org.mockito.Mockito.verifyNoInteractions(perfumeManager);
	}

	@Test
	public void testWhenPerfumeIsUpdatedThenListShouldContainUpdatedPerfume() {
		Perfume original = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume updated = new Perfume("p001", "Sauvage Elixir", "Dior", "Spicy", 60, 4.8);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(original));
			perfumeSwingView.perfumeUpdated(updated);
		});

		assertThat(window.list("perfumeList").contents()[0]).isEqualTo(updated.toString());

		window.list("perfumeList").requireSelection(0);
	}

	@Test
	public void testWhenUpdatingUnknownPerfumeThenListShouldRemainUnchanged() {
		Perfume existing = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume unknown = new Perfume("p002", "Bleu", "Chanel", "Fresh", 100, 4.0);

		GuiActionRunner.execute(() -> {
			perfumeSwingView.showAllPerfumes(java.util.List.of(existing));
			perfumeSwingView.perfumeUpdated(unknown);
		});

		assertThat(window.list("perfumeList").contents()).containsExactly(existing.toString());

		assertThat(window.list("perfumeList").selection()).isEmpty();
	}

	@Test
	public void testWhenAddButtonIsClickedWithInvalidNumbersThenErrorShouldAppear() {
		window.textBox("idTextBox").enterText("invalid001");
		window.textBox("nameTextBox").enterText("Invalid Perfume");
		window.textBox("volumeTextBox").enterText("not-a-number");
		window.textBox("ratingTextBox").enterText("4.5");

		window.button(JButtonMatcher.withText("Add")).click();

		window.label("errorMessageLabel").requireText("Volume and rating must be valid numbers.");
	}

	@Test
	public void testWhenUpdateActionIsTriggeredWithoutSelectionThenManagerShouldNotUpdatePerfume() {
		JButton updateButton = window.button(JButtonMatcher.withText("Update")).target();

		updateButton.getActionListeners()[0]
				.actionPerformed(new ActionEvent(updateButton, ActionEvent.ACTION_PERFORMED, "Update"));

		org.mockito.Mockito.verifyNoInteractions(perfumeManager);
	}

	@Test
	public void testWhenDeleteActionIsTriggeredWithoutSelectionThenManagerShouldNotDeletePerfume() {
		JButton deleteButton = window.button(JButtonMatcher.withText("Delete Selected")).target();

		deleteButton.getActionListeners()[0]
				.actionPerformed(new ActionEvent(deleteButton, ActionEvent.ACTION_PERFORMED, "Delete Selected"));

		org.mockito.Mockito.verifyNoInteractions(perfumeManager);
	}

	@Test
	public void testChangedUpdateShouldRefreshAddButton() {
		window.textBox("idTextBox").enterText("changed001");
		window.textBox("nameTextBox").enterText("Changed Perfume");

		AbstractDocument document = (AbstractDocument) window.textBox("idTextBox").target().getDocument();

		DocumentListener targetListener = null;

		for (DocumentListener listener : document.getDocumentListeners()) {
			if (listener.getClass().getName().startsWith("com.example.perfumemanager.view.swing.PerfumeSwingView")) {
				targetListener = listener;
				break;
			}
		}

		assertThat(targetListener).isNotNull();

		targetListener.changedUpdate(null);

		window.button(JButtonMatcher.withText("Add")).requireEnabled();
	}
}