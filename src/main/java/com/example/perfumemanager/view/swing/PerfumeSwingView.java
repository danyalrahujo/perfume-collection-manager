package com.example.perfumemanager.view.swing;

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
	 * Create the frame.
	 */
	public PerfumeSwingView() {

		setTitle("Perfume View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 472);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();

		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };

		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };

		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };

		contentPane.setLayout(gbl_contentPane);

		JLabel lblId = new JLabel("id");

		GridBagConstraints gbcLblId = new GridBagConstraints();
		gbcLblId.insets = new Insets(0, 0, 5, 5);
		gbcLblId.anchor = GridBagConstraints.EAST;
		gbcLblId.gridx = 0;
		gbcLblId.gridy = 0;

		contentPane.add(lblId, gbcLblId);

		txtId = new JTextField();
		txtId.setName("idTextBox");

		GridBagConstraints gbcId = new GridBagConstraints();
		gbcId.insets = new Insets(0, 0, 5, 0);
		gbcId.fill = GridBagConstraints.HORIZONTAL;
		gbcId.gridx = 1;
		gbcId.gridy = 0;

		contentPane.add(txtId, gbcId);

		txtId.setColumns(10);

		JLabel lblName = new JLabel("name");

		GridBagConstraints gbcLblName = new GridBagConstraints();
		gbcLblName.anchor = GridBagConstraints.EAST;
		gbcLblName.insets = new Insets(0, 0, 5, 5);
		gbcLblName.gridx = 0;
		gbcLblName.gridy = 1;

		contentPane.add(lblName, gbcLblName);

		txtName = new JTextField();
		txtName.setName("nameTextBox");

		GridBagConstraints gbcName = new GridBagConstraints();
		gbcName.insets = new Insets(0, 0, 5, 0);
		gbcName.fill = GridBagConstraints.HORIZONTAL;
		gbcName.gridx = 1;
		gbcName.gridy = 1;

		contentPane.add(txtName, gbcName);

		txtName.setColumns(10);

		JLabel lblBrand = new JLabel("brand");

		GridBagConstraints gbcLblBrand = new GridBagConstraints();
		gbcLblBrand.anchor = GridBagConstraints.EAST;
		gbcLblBrand.insets = new Insets(0, 0, 5, 5);
		gbcLblBrand.gridx = 0;
		gbcLblBrand.gridy = 2;

		contentPane.add(lblBrand, gbcLblBrand);

		txtBrand = new JTextField();
		txtBrand.setName("brandTextBox");

		GridBagConstraints gbcBrand = new GridBagConstraints();
		gbcBrand.insets = new Insets(0, 0, 5, 0);
		gbcBrand.fill = GridBagConstraints.HORIZONTAL;
		gbcBrand.gridx = 1;
		gbcBrand.gridy = 2;

		contentPane.add(txtBrand, gbcBrand);

		txtBrand.setColumns(10);

		JLabel lblFragranceFamily = new JLabel("fragrance family");

		GridBagConstraints gbcLblFragranceFamily = new GridBagConstraints();

		gbcLblFragranceFamily.anchor = GridBagConstraints.EAST;
		gbcLblFragranceFamily.insets = new Insets(0, 0, 5, 5);
		gbcLblFragranceFamily.gridx = 0;
		gbcLblFragranceFamily.gridy = 3;

		contentPane.add(lblFragranceFamily, gbcLblFragranceFamily);

		txtFragranceFamily = new JTextField();
		txtFragranceFamily.setName("fragrancefamilyTextBox");

		GridBagConstraints gbcFragranceFamily = new GridBagConstraints();

		gbcFragranceFamily.insets = new Insets(0, 0, 5, 0);
		gbcFragranceFamily.fill = GridBagConstraints.HORIZONTAL;
		gbcFragranceFamily.gridx = 1;
		gbcFragranceFamily.gridy = 3;

		contentPane.add(txtFragranceFamily, gbcFragranceFamily);

		txtFragranceFamily.setColumns(10);

		JLabel lblVolume = new JLabel("volume");

		GridBagConstraints gbcLblVolume = new GridBagConstraints();

		gbcLblVolume.anchor = GridBagConstraints.EAST;
		gbcLblVolume.insets = new Insets(0, 0, 5, 5);
		gbcLblVolume.gridx = 0;
		gbcLblVolume.gridy = 4;

		contentPane.add(lblVolume, gbcLblVolume);

		txtVolume = new JTextField();
		txtVolume.setName("volumeTextBox");

		GridBagConstraints gbcVolume = new GridBagConstraints();

		gbcVolume.insets = new Insets(0, 0, 5, 0);
		gbcVolume.fill = GridBagConstraints.HORIZONTAL;
		gbcVolume.gridx = 1;
		gbcVolume.gridy = 4;

		contentPane.add(txtVolume, gbcVolume);

		txtVolume.setColumns(10);

		JLabel lblRating = new JLabel("rating");

		GridBagConstraints gbcLblRating = new GridBagConstraints();

		gbcLblRating.anchor = GridBagConstraints.EAST;
		gbcLblRating.insets = new Insets(0, 0, 5, 5);
		gbcLblRating.gridx = 0;
		gbcLblRating.gridy = 5;

		contentPane.add(lblRating, gbcLblRating);

		txtRating = new JTextField();
		txtRating.setName("ratingTextBox");

		GridBagConstraints gbcRating = new GridBagConstraints();

		gbcRating.insets = new Insets(0, 0, 5, 0);
		gbcRating.fill = GridBagConstraints.HORIZONTAL;
		gbcRating.gridx = 1;
		gbcRating.gridy = 5;

		contentPane.add(txtRating, gbcRating);

		txtRating.setColumns(10);

		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);

		btnAdd.addActionListener(e -> addPerfume());

		DocumentListener addButtonListener = new DocumentListener() {

			private void updateAddButton() {
				boolean enabled = !txtId.getText().trim().isEmpty() && !txtName.getText().trim().isEmpty();

				btnAdd.setEnabled(enabled);
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

		GridBagConstraints gbcBtnAdd = new GridBagConstraints();

		gbcBtnAdd.insets = new Insets(0, 0, 5, 0);
		gbcBtnAdd.gridwidth = 2;
		gbcBtnAdd.gridx = 0;
		gbcBtnAdd.gridy = 6;

		contentPane.add(btnAdd, gbcBtnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);

		btnUpdate.addActionListener(e -> updatePerfume());

		GridBagConstraints gbcBtnUpdate = new GridBagConstraints();

		gbcBtnUpdate.insets = new Insets(0, 0, 5, 0);
		gbcBtnUpdate.gridwidth = 2;
		gbcBtnUpdate.gridx = 0;
		gbcBtnUpdate.gridy = 7;

		contentPane.add(btnUpdate, gbcBtnUpdate);

		JScrollPane scrollPane = new JScrollPane();

		GridBagConstraints gbcScrollPane = new GridBagConstraints();

		gbcScrollPane.insets = new Insets(0, 0, 5, 0);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridwidth = 2;
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 8;

		contentPane.add(scrollPane, gbcScrollPane);

		perfumeListModel = new DefaultListModel<>();

		perfumeList = new JList<>(perfumeListModel);

		perfumeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		perfumeList.setName("perfumeList");

		scrollPane.setViewportView(perfumeList);

		btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.setEnabled(false);

		btnDeleteSelected.addActionListener(e -> deleteSelectedPerfume());

		GridBagConstraints gbcBtnDelete = new GridBagConstraints();

		gbcBtnDelete.insets = new Insets(0, 0, 5, 0);
		gbcBtnDelete.gridwidth = 2;
		gbcBtnDelete.gridx = 0;
		gbcBtnDelete.gridy = 9;

		contentPane.add(btnDeleteSelected, gbcBtnDelete);

		perfumeList.addListSelectionListener(e -> {

			if (e.getValueIsAdjusting()) {
				return;
			}

			Perfume selectedPerfume = perfumeList.getSelectedValue();

			boolean selected = selectedPerfume != null;

			btnDeleteSelected.setEnabled(selected);
			btnUpdate.setEnabled(selected);

			if (selectedPerfume != null) {

				txtId.setText(selectedPerfume.getId());

				txtName.setText(selectedPerfume.getName());

				txtBrand.setText(selectedPerfume.getBrand());

				txtFragranceFamily.setText(selectedPerfume.getFragranceFamily());

				txtVolume.setText(String.valueOf(selectedPerfume.getVolume()));

				txtRating.setText(String.valueOf(selectedPerfume.getRating()));
			}
		});

		lblErrorMessage = new JLabel(" ");
		lblErrorMessage.setName("errorMessageLabel");

		GridBagConstraints gbcLblError = new GridBagConstraints();

		gbcLblError.insets = new Insets(0, 0, 0, 5);

		gbcLblError.gridx = 0;
		gbcLblError.gridy = 10;

		contentPane.add(lblErrorMessage, gbcLblError);
	}

	public void setPerfumeManager(PerfumeManager perfumeManager) {

		this.perfumeManager = perfumeManager;
	}

	private void addPerfume() {

		try {

			Perfume perfume = new Perfume(txtId.getText(), txtName.getText(), txtBrand.getText(),
					txtFragranceFamily.getText(), Integer.parseInt(txtVolume.getText()),
					Double.parseDouble(txtRating.getText()));

			perfumeManager.addPerfume(perfume);

			clearFields();

		} catch (NumberFormatException e) {

			showError("Volume and rating must be valid numbers.", null);
		}
	}

	private void updatePerfume() {

		if (perfumeList.getSelectedIndex() < 0) {
			return;
		}

		try {

			Perfume updatedPerfume = new Perfume(txtId.getText(), txtName.getText(), txtBrand.getText(),
					txtFragranceFamily.getText(), Integer.parseInt(txtVolume.getText()),
					Double.parseDouble(txtRating.getText()));

			perfumeManager.updatePerfume(updatedPerfume);

		} catch (NumberFormatException e) {

			showError("Volume and rating must be valid numbers.", null);
		}
	}

	private void deleteSelectedPerfume() {

		int selectedIndex = perfumeList.getSelectedIndex();

		if (selectedIndex < 0) {
			return;
		}

		Perfume selectedPerfume = perfumeListModel.getElementAt(selectedIndex);

		perfumeManager.deletePerfume(selectedPerfume);
	}

	private void clearFields() {

		perfumeList.clearSelection();

		txtId.setText("");
		txtName.setText("");
		txtBrand.setText("");
		txtFragranceFamily.setText("");
		txtVolume.setText("");
		txtRating.setText("");

		btnUpdate.setEnabled(false);
		btnDeleteSelected.setEnabled(false);
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

		perfumeList.clearSelection();

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