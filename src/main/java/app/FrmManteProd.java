package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import lombok.Builder.Default;
import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable table;
	private DefaultTableModel modelo;
	JComboBox cboCategorias;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		/*txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);*/
		
		
		modelo = new DefaultTableModel();
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		
		modelo.addColumn("ID Producto");
		modelo.addColumn("Descripción");
		modelo.addColumn("Stock");
		modelo.addColumn("precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Estado");
		
		
		
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 30, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(122, 50, 120, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(122, 70, 80, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(122, 90, 80, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Descripcion :");
		lblNewLabel1.setBounds(10, 50, 102, 14);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("Stock:");
		lblNewLabel2.setBounds(10, 70, 102, 14);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("Precio:");
		lblNewLabel3.setBounds(10, 90, 102, 14);
		contentPane.add(lblNewLabel3);
		

		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 128, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 130, 102, 14);
		contentPane.add(lblCategora);
		
		llenarCombo();
	}

	void listado() {
		List<Producto> lstProducto = em.createQuery(" SELECT p FROM Producto p", Producto.class).getResultList();
		
		
		if( lstProducto == null){
			mensaje("LISTA VACIA");
		}
		else{
			modelo.setRowCount(0);
			for(Producto p : lstProducto){
				
				Object[] fila ={
					p.getIdprod(),
					p.getDescripcion(),
					p.getStock(),
					p.getPrecio(),
					p.getIdcategoria(),
					p.getEstado()
				
				};
				modelo.addRow(fila);
			}
			
		}
		

	}
	
	void llenarCombo() {
		//Listado de tipo Categoria
		
		List<Categoria> lstcategoria = em.createQuery(" SELECT c FROM Categoria c", Categoria.class).getResultList();
		cboCategorias.addItem("Seleccione");
		for(Categoria c : lstcategoria) {
			 cboCategorias.addItem(c.getDescripcion());
		 }
	}
	
	void registrar() {
		int estado =1;
		
		//proceso 
		
		
		Producto p = new Producto();
		
		p.setIdprod(leerCodigo());
		p.setDescripcion(leerDescripcion());
		p.setStock(leerStock());
		p.setPrecio(leerPrecio());
		p.setIdcategoria(leerCategoria());
		p.setEstado(estado);
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		//salida
		
	}
	
	String leerCodigo(){
		return txtCódigo.getText();
	}
	
	String leerDescripcion(){
		return txtCódigo.getText();
	}
	
	int leerStock(){
		return Integer.parseInt(txtStock.getText());
	}
	
	double leerPrecio(){
		return Double.parseDouble(txtPrecio.getText());
	}
	
	int leerCategoria() {
		return cboCategorias.getSelectedIndex();
	}
	
	void mensaje(String cad){
    	JOptionPane.showMessageDialog(null, cad);
    }
    
	
	//conecion a la base de Datos
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
}
