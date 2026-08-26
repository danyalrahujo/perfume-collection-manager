package com.example.perfumemanager.view.swing;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.example.perfumemanager.controller.PerfumeManager;
import com.example.perfumemanager.model.Perfume;
import com.example.perfumemanager.repository.InMemoryPerfumeRepository;
import com.example.perfumemanager.repository.PerfumeRepository;
import com.example.perfumemanager.view.PerfumeView;

public class PerfumeSwingView extends JFrame implements PerfumeView {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtBrand;
	private JTextField txtFragranceFamily;
	private JTextField txtVolume;
	private JTextField txtRating;

	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDeleteSelected;

	private JList<Perfume> perfumeList;
	private JLabel lblErrorMessage;

	private DefaultListModel<Perfume> perfumeListModel;

	private PerfumeManager perfumeManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfumeSwingView frame = new PerfumeSwingView();

					PerfumeRepository repository = new InMemoryPerfumeRepository();

					PerfumeManager perfumeManager = new PerfumeManager(repository, frame);

					frame.setPerfumeManager(perfumeManager);

					perfumeManager.listPerfumes();

					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PerfumeSwingView() {
		setTitle("Perfume View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();

		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };

		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };

		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };

		contentPane.setLayout(gbl_contentPane);

		// ID
		JLabel lblNewLabel = new JLabel("id");

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		txtId = new JTextField();
		txtId.setName("idTextBox");

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(txtId, gbc_textField);
		txtId.setColumns(10);

		// Name
		JLabel lblNewLabel_1 = new JLabel("name");

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtName = new JTextField();
		txtName.setName("nameTextBox");

		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(txtName, gbc_textField_1);
		txtName.setColumns(10);

		// Brand
		JLabel lblNewLabel_2 = new JLabel("brand");

		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtBrand = new JTextField();
		txtBrand.setName("brandTextBox");

		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		contentPane.add(txtBrand, gbc_textField_2);
		txtBrand.setColumns(10);

		// Fragrance family
		JLabel lblNewLabel_3 = new JLabel("fragrance family");

		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtFragranceFamily = new JTextField();
		txtFragranceFamily.setName("fragrancefamilyTextBox");

		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		contentPane.add(txtFragranceFamily, gbc_textField_3);
		txtFragranceFamily.setColumns(10);

		// Volume
		JLabel lblNewLabel_4 = new JLabel("volume");

		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		txtVolume = new JTextField();
		txtVolume.setName("volumeTextBox");

		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		contentPane.add(txtVolume, gbc_textField_4);
		txtVolume.setColumns(10);

		// Rating
		JLabel lblNewLabel_5 = new JLabel("rating");

		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		txtRating = new JTextField();
		txtRating.setName("ratingTextBox");

		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 5;
		contentPane.add(txtRating, gbc_textField_5);
		txtRating.setColumns(10);

		// Add button
		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(e -> addPerfume());

		DocumentListener addButtonListener = new DocumentListener() {

			private void updateAddButton() {
				btnAdd.setEnabled(!txtId.getText().trim().isEmpty() && !txtName.getText().trim().isEmpty());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateAddButton();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateAddButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateAddButton();
			}
		};

		txtId.getDocument().addDocumentListener(addButtonListener);
		txtName.getDocument().addDocumentListener(addButtonListener);

		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridwidth = 2;
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 6;
		contentPane.add(btnAdd, gbc_btnAdd);

		// Update button
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(e -> updatePerfume());

		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.gridwidth = 2;
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 7;
		contentPane.add(btnUpdate, gbc_btnUpdate);

		// Perfume list
		JScrollPane scrollPane = new JScrollPane();

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 8;
		contentPane.add(scrollPane, gbc_scrollPane);

		perfumeListModel = new DefaultListModel<>();

		perfumeList = new JList<>(perfumeListModel);
		scrollPane.setViewportView(perfumeList);

		perfumeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		perfumeList.setName("perfumeList");

		// Delete button
		btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.setEnabled(false);

		btnDeleteSelected.addActionListener(e -> {
			Perfume selectedPerfume = perfumeList.getSelectedValue();

			if (selectedPerfume != null) {
				perfumeManager.deletePerfume(selectedPerfume);
			}
		});

		// Selection listener
		perfumeList.addListSelectionListener(e -> {

			Perfume selectedPerfume = perfumeList.getSelectedValue();

			btnDeleteSelected.setEnabled(selectedPerfume != null);
			btnUpdate.setEnabled(selectedPerfume != null);

			if (selectedPerfume != null) {
				txtId.setText(selectedPerfume.getId());
				txtName.setText(selectedPerfume.getName());
				txtBrand.setText(selectedPerfume.getBrand());
				txtFragranceFamily.setText(selectedPerfume.getFragranceFamily());
				txtVolume.setText(String.valueOf(selectedPerfume.getVolume()));
				txtRating.setText(String.valueOf(selectedPerfume.getRating()));
			}
		});

		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridwidth = 2;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 9;
		contentPane.add(btnDeleteSelected, gbc_btnDelete);

		// Error message
		lblErrorMessage = new JLabel(" ");
		lblErrorMessage.setName("errorMessageLabel");

		GridBagConstraints gbc_lblErrorMessage = new GridBagConstraints();
		gbc_lblErrorMessage.insets = new Insets(0, 0, 0, 5);
		gbc_lblErrorMessage.gridx = 0;
		gbc_lblErrorMessage.gridy = 10;
		contentPane.add(lblErrorMessage, gbc_lblErrorMessage);
	}

	public void setPerfumeManager(PerfumeManager perfumeManager) {
		this.perfumeManager = perfumeManager;
	}

	private void addPerfume() {
		Perfume perfume = new Perfume(txtId.getText(), txtName.getText(), txtBrand.getText(),
				txtFragranceFamily.getText(), Integer.parseInt(txtVolume.getText()),
				Double.parseDouble(txtRating.getText()));

		perfumeManager.addPerfume(perfume);

		txtId.setText("");
		txtName.setText("");
		txtBrand.setText("");
		txtFragranceFamily.setText("");
		txtVolume.setText("");
		txtRating.setText("");
	}

	private void updatePerfume() {
		if (perfumeList.getSelectedValue() != null) {
			Perfume updatedPerfume = new Perfume(txtId.getText(), txtName.getText(), txtBrand.getText(),
					txtFragranceFamily.getText(), Integer.parseInt(txtVolume.getText()),
					Double.parseDouble(txtRating.getText()));

			perfumeManager.updatePerfume(updatedPerfume);
		}
	}

	@Override
	public void showAllPerfumes(List<Perfume> perfumes) {
		perfumeListModel.clear();

		for (Perfume perfume : perfumes) {
			perfumeListModel.addElement(perfume);
		}
	}

	@Override
	public void showError(String message, Perfume perfume) {
		lblErrorMessage.setText(message);
	}

	@Override
	public void perfumeAdded(Perfume perfume) {
		perfumeListModel.addElement(perfume);
	}

	@Override
	public void perfumeRemoved(Perfume perfume) {
		perfumeListModel.removeElement(perfume);
		btnDeleteSelected.setEnabled(false);
		btnUpdate.setEnabled(false);
	}

	@Override
	public void perfumeUpdated(Perfume perfume) {
		for (int i = 0; i < perfumeListModel.size(); i++) {
			Perfume existingPerfume = perfumeListModel.getElementAt(i);

			if (existingPerfume.getId().equals(perfume.getId())) {
				perfumeListModel.setElementAt(perfume, i);
				perfumeList.setSelectedIndex(i);
				return;
			}
		}
	}
}