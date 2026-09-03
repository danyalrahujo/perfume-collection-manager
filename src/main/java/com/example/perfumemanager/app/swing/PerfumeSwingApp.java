package com.example.perfumemanager.app.swing;

import java.awt.EventQueue;

import com.example.perfumemanager.controller.PerfumeManager;
import com.example.perfumemanager.repository.MongoPerfumeRepository;
import com.example.perfumemanager.view.swing.PerfumeSwingView;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class PerfumeSwingApp {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			try {

				String mongoHost = args.length > 0 ? args[0] : "localhost";
				int mongoPort = args.length > 1 ? Integer.parseInt(args[1]) : 27017;

				String databaseName = args.length > 2 ? args[2] : "perfume_manager";
				String collectionName = args.length > 3 ? args[3] : "perfumes";

				MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);

				MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoClient, databaseName,
						collectionName);

				PerfumeSwingView view = new PerfumeSwingView();

				PerfumeManager perfumeManager = new PerfumeManager(repository, view);

				view.setPerfumeManager(perfumeManager);

				view.setVisible(true);

				perfumeManager.listPerfumes();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}