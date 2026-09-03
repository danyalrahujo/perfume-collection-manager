package com.example.perfumemanager;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.mongodb.MongoDBContainer;

import com.example.perfumemanager.view.swing.PerfumeSwingView;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class PerfumeSwingViewE2E {

	private static final MongoDBContainer mongoDB = new MongoDBContainer("mongo:7.0");

	private static MongoClient mongoClient;

	private Robot robot;
	private FrameFixture window;

	@BeforeClass
	public static void startMongoDB() {
		mongoDB.start();

		mongoClient = MongoClients.create(mongoDB.getConnectionString());
	}

	@AfterClass
	public static void stopMongoDB() {
		if (mongoClient != null) {
			mongoClient.close();
		}

		mongoDB.stop();
	}

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		mongoClient.getDatabase("perfume_manager").getCollection("perfumes").deleteMany(new Document());
	}

	@After
	public void tearDown() {
		if (window != null) {
			window.cleanUp();
		}

		if (robot != null) {
			robot.cleanUp();
		}
	}

	private void startApplication() {
		application("com.example.perfumemanager.app.swing.PerfumeSwingApp").withArgs(mongoDB.getHost(),
				String.valueOf(mongoDB.getMappedPort(27017)), "perfume_manager", "perfumes").start();

		window = findFrame(PerfumeSwingView.class).using(robot);
	}

	@Test
	public void shouldAddUpdateAndDeletePerfumeThroughGui() {

		startApplication();

		window.textBox("idTextBox").enterText("e2e001");
		window.textBox("nameTextBox").enterText("E2E Perfume");
		window.textBox("brandTextBox").enterText("Test Brand");
		window.textBox("fragrancefamilyTextBox").enterText("Woody");
		window.textBox("volumeTextBox").enterText("100");
		window.textBox("ratingTextBox").enterText("4.5");

		window.button(JButtonMatcher.withText("Add")).click();

		window.list("perfumeList").requireItemCount(1);

		window.list("perfumeList").selectItem(0);

		window.textBox("nameTextBox").deleteText();
		window.textBox("nameTextBox").enterText("Updated E2E Perfume");

		window.button(JButtonMatcher.withText("Update")).click();

		window.list("perfumeList").requireItemCount(1);

		window.list("perfumeList").selectItem(0);

		window.button(JButtonMatcher.withText("Delete Selected")).click();

		robot.waitForIdle();

		window.list("perfumeList").requireItemCount(0);
	}

	@Test
	public void shouldAddAndDeletePerfumeThroughGui() {

		startApplication();

		window.textBox("idTextBox").enterText("delete001");
		window.textBox("nameTextBox").enterText("Delete Test Perfume");
		window.textBox("brandTextBox").enterText("Test Brand");
		window.textBox("fragrancefamilyTextBox").enterText("Woody");
		window.textBox("volumeTextBox").enterText("100");
		window.textBox("ratingTextBox").enterText("4.5");

		window.button(JButtonMatcher.withText("Add")).click();

		window.list("perfumeList").requireItemCount(1);

		window.list("perfumeList").selectItem(0);

		window.button(JButtonMatcher.withText("Delete Selected")).click();

		robot.waitForIdle();

		window.list("perfumeList").requireItemCount(0);
	}

	@Test
	public void shouldDisplayExistingPerfumeWhenApplicationStarts() {

		mongoClient.getDatabase("perfume_manager").getCollection("perfumes")
				.insertOne(new Document("_id", "startup001").append("name", "Sauvage").append("brand", "Dior")
						.append("fragranceFamily", "Woody").append("volume", 100).append("rating", 4.5));

		startApplication();

		window.list("perfumeList").requireItemCount(1);
	}
}