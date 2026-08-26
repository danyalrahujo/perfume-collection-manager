package com.example.perfumemanager.view.swing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

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

		window.label("errorMessageLabel").requireText(" ");
	}

	@Test
	public void testWhenIdAndNameAreNonEmptyThenAddButtonShouldBeEnabled() {
		window.textBox("idTextBox").enterText("1");
		window.textBox("nameTextBox").enterText("test");

		window.button(JButtonMatcher.withText("Add")).requireEnabled();
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

		window.textBox("idTextBox").requireText("");
		window.textBox("nameTextBox").requireText("");
		window.textBox("brandTextBox").requireText("");
		window.textBox("fragrancefamilyTextBox").requireText("");
		window.textBox("volumeTextBox").requireText("");
		window.textBox("ratingTextBox").requireText("");
	}

	@Test
	public void testWhenPerfumeIsSelectedThenDeleteButtonShouldBeEnabled() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		GuiActionRunner.execute(() -> perfumeSwingView.showAllPerfumes(java.util.List.of(perfume)));

		window.list("perfumeList").selectItem(0);

		window.button(JButtonMatcher.withText("Delete Selected")).requireEnabled();
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
}