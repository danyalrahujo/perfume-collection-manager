package com.example.perfumemanager.view.swing;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.assertj.swing.core.matcher.JButtonMatcher;

import com.example.perfumemanager.controller.PerfumeManager;
import com.example.perfumemanager.model.Perfume;
import com.example.perfumemanager.repository.InMemoryPerfumeRepository;
import com.example.perfumemanager.repository.PerfumeRepository;

@RunWith(GUITestRunner.class)
public class PerfumeSwingViewIT extends AssertJSwingJUnitTestCase {

	private PerfumeSwingView perfumeSwingView;
	private FrameFixture window;
	private PerfumeRepository repository;

	@Override
	protected void onSetUp() {
		repository = new InMemoryPerfumeRepository();

		perfumeSwingView = GuiActionRunner.execute(() -> {
			PerfumeSwingView view = new PerfumeSwingView();
			PerfumeManager perfumeManager = new PerfumeManager(repository, view);
			view.setPerfumeManager(perfumeManager);
			return view;
		});

		window = new FrameFixture(robot(), perfumeSwingView);
		window.show();
	}

	@Test
	public void shouldAddPerfumeThroughUiAndStoreItInRepository() {
		window.textBox("idTextBox").enterText("it001");
		window.textBox("nameTextBox").enterText("Integration Perfume");
		window.textBox("brandTextBox").enterText("Test Brand");
		window.textBox("fragrancefamilyTextBox").enterText("Woody");
		window.textBox("volumeTextBox").enterText("100");
		window.textBox("ratingTextBox").enterText("4.5");

		window.button(JButtonMatcher.withText("Add")).click();

		Perfume expected = new Perfume("it001", "Integration Perfume", "Test Brand", "Woody", 100, 4.5);

		assertThat(repository.findAll()).containsExactly(expected);
		window.list("perfumeList").requireItemCount(1);
		assertThat(window.list("perfumeList").contents()[0]).isEqualTo(expected.toString());
	}

	@Test
	public void shouldDeletePerfumeThroughUiAndRemoveItFromRepository() {
		Perfume perfume = new Perfume("it002", "Delete Integration Perfume", "Test Brand", "Fresh", 100, 4.0);

		repository.save(perfume);

		GuiActionRunner.execute(() -> perfumeSwingView.showAllPerfumes(repository.findAll()));

		window.list("perfumeList").selectItem(0);
		window.button(JButtonMatcher.withText("Delete Selected")).click();

		assertThat(repository.findAll()).isEmpty();
		window.list("perfumeList").requireItemCount(0);
	}

	@Override
	protected void onTearDown() {
		window.cleanUp();
	}
}