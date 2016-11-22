package com.acer.mvc.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.acer.mvc.controller.ControllerFactory;
import com.acer.mvc.controller.IController;
import com.acer.mvc.vo.Item;
import com.acer.mvc.vo.UserVo;

public class GridLayoutFrame extends JFrame // 建構子
{

	private static final long serialVersionUID = 1L;

	// UI Container
	private Container container;
	private ControllerFactory controllerFactory;
	private IController controller;
	// UI Component
	private JTextArea jName;
	private JTextArea jAge;
	private JTextArea jEmail;
	private JTextArea jPhone;
	private JRadioButton jMan;
	private JRadioButton jFemale;
	private ButtonGroup buttonGroup;
	private JComboBox<Item> jListCity;
	private JComboBox<Item> jListTown;
	private JButton sumbit;

	// Label
	private JLabel lName;
	private JLabel lAge;
	private JLabel lSex;
	private JLabel lAddress;
	private JLabel lEmail;
	private JLabel lPhone;
	private JLabel lErrEmail;
	private JLabel lErrPhone;
	private JLabel empty;
	private Map<String, String> viewmsgMap;
	private List<Map<String, String>> cityMapList;
	private List<Map<String, String>> townMapList;
	UserVo vo = new UserVo();
	// Controller controller = new Controller(this);

	public GridLayoutFrame() {
		super("GridLayout");
		controllerFactory = new ControllerFactory();
		controller = controllerFactory.getInstance();
		// Controller controller = new Controller(this);
		cityMapList = controller.getListMapCity();

		// Name label
		lName = new JLabel("*姓名：", SwingConstants.CENTER);
		lName.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		jName = new JTextArea();

		// Age label
		lAge = new JLabel("*年齡：", SwingConstants.CENTER);
		lAge.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		jAge = new JTextArea();

		// Sex label
		lSex = new JLabel("*性別：", SwingConstants.CENTER);
		lSex.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		jMan = new JRadioButton("男", true);
		jFemale = new JRadioButton("女");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jMan);
		buttonGroup.add(jFemale);

		// Address label
		lAddress = new JLabel("*地址：", SwingConstants.CENTER);
		lAddress.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		// Model model = new Model();

		// ComboBox
		jListCity = new JComboBox<Item>();
		jListTown = new JComboBox<Item>();
		// setCityItems
		for (Map<String, String> mapCity : cityMapList) {
			jListCity.addItem(new Item(mapCity.get("provinceNo"), mapCity.get("cityNo"), mapCity.get("cityName")));
		}
		// init towns
		Item cityItem = (Item) jListCity.getSelectedItem();
		townMapList = controller.getFilterTowns(cityItem.getValue(), cityItem.getValue2());
		setTownsComboBox(townMapList);

		// onchangeCity even
		jListCity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item item = (Item) comboBox.getSelectedItem();
				townMapList = controller.getFilterTowns(item.getValue(), item.getValue2());
				setTownsComboBox(townMapList);
			}
		});
		// E-mail label
		lEmail = new JLabel("*E-Mail：", SwingConstants.CENTER);
		lEmail.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		jEmail = new JTextArea();
		lErrEmail = new JLabel("");

		// Phone label
		lPhone = new JLabel("*手機號碼：", SwingConstants.CENTER);
		lPhone.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		jPhone = new JTextArea();
		lErrPhone = new JLabel("");

		empty = new JLabel("*為必填欄位!!", SwingConstants.CENTER);
		empty.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		sumbit = new JButton("Submit");
		sumbit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chekcValue = JOptionPane.showConfirmDialog(null, "是否送出", "送出確認", JOptionPane.YES_NO_OPTION);
				vo = new UserVo();
				vo.setName(jName.getText());
				vo.setAge(jAge.getText());
				if (jMan.isSelected()) {
					vo.setSex("男");
				} else {
					vo.setSex("女");
				}
				Item cityItem = (Item) jListCity.getSelectedItem();
				Item townItem = (Item) jListTown.getSelectedItem();
				if (cityItem != null) {
					vo.setCity(cityItem.getDescription());
				}
				if (townItem != null) {
					vo.setTown(townItem.getDescription());
				}
				vo.setPhone(jPhone.getText());
				vo.setEmail(jEmail.getText());
				// Yes:0, NO:1, cancel:-1
				if (chekcValue == 0) {
					viewmsgMap = controller.doSubmit(vo);
					if(viewmsgMap != null && viewmsgMap.size() > 0) {
						refresherrormessage(viewmsgMap);
					}
					else {
						//清空錯誤訊息
						refresherrormessage(viewmsgMap);
						JOptionPane.showMessageDialog(null, "存檔成功");
						
					}
				}
			}
		});

		// initial container
		container = this.getContentPane();
		container.setLayout(new GridLayout(10, 5, 15, 15));
		container.add(new JLabel(""));
		container.add(new JLabel(""));
		container.add(new JLabel(""));
		container.add(lName);
		container.add(jName);
		container.add(new JLabel(""));
		container.add(lAge);
		container.add(jAge);
		container.add(new JLabel(""));
		container.add(lSex);
		container.add(jMan);
		container.add(jFemale);
		container.add(lAddress);
		container.add(jListCity);
		container.add(jListTown);
		container.add(lEmail);
		container.add(jEmail);
		container.add(lErrEmail);
		container.add(lPhone);
		container.add(jPhone);
		container.add(lErrPhone);
		container.add(new JLabel(""));
		container.add(new JLabel(""));
		container.add(empty);
		container.add(new JLabel(""));
		container.add(new JLabel(""));
		container.add(sumbit);

	}

	public void refreshView(UserVo userVo) {
		this.jName.setText(userVo.getName());
		this.jAge.setText(userVo.getAge());
		this.jEmail.setText(userVo.getEmail());
		this.jPhone.setText(userVo.getPhone());
		if ("男".equals(userVo.getSex())) {
			this.jMan.setSelected(true);
			this.jFemale.setSelected(false);
		} else {
			this.jMan.setSelected(false);
			this.jFemale.setSelected(true);
		}
		this.jListCity.setSelectedItem(userVo.getCity());
		this.jListTown.setSelectedItem(userVo.getTown());
	}

	public void refresherrormessage(Map<String, String> errorMap) {
		viewmsgMap = errorMap;
		lErrEmail.setText(viewmsgMap.get("email"));
		lErrPhone.setText(viewmsgMap.get("phone"));
		
		if(viewmsgMap.get("userExist") != null) {
			JOptionPane.showMessageDialog(null,
				    "使用者已存在",
				    "Waring",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(viewmsgMap.get("userSaveErr") != null) {
			JOptionPane.showMessageDialog(null,
					"存檔失敗，請聯絡客服",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void setTownsComboBox(List<Map<String, String>> townsMapList) {
		if (townsMapList != null) {
			jListTown.removeAllItems();
			for (Map<String, String> mapTown : townsMapList) {
				jListTown.addItem(new Item(mapTown.get("provinceNo"), mapTown.get("cityNo"), mapTown.get("areaName")));
			}
		}
	}

	public JTextArea getjName() {
		return jName;
	}

	public JTextArea getjAge() {
		return jAge;
	}

	public JTextArea getjEmail() {
		return jEmail;
	}

	public JTextArea getjPhone() {
		return jPhone;
	}

	public JRadioButton getjMan() {
		return jMan;
	}

	public JRadioButton getjFemale() {
		return jFemale;
	}

	public JComboBox<Item> getjListCity() {
		return jListCity;
	}

	public void setjListCity(JComboBox<Item> jListCity) {
		this.jListCity = jListCity;
	}

	public JComboBox<Item> getjListTown() {
		return jListTown;
	}

	public CityActionListener getCityActionListener() {
		return this.getCityActionListener();
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

	/**
	 * 
	 * new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) { Controller
	 *           controller = new Controller();
	 * 
	 *           UserVo vo = new UserVo(); vo.setName(jName.toString()); //
	 *           vo.setAge(age); // vo.setCity(city); // vo.setTown(town); //
	 *           vo.setSex(sex); vo.setPhone(jPhone.toString());
	 *           vo.setEmail(jEmail.getText());
	 * 
	 *           System.out.println(">> email.getText() = " + jEmail.getText());
	 * 
	 *           Map map = controller.doSubmit(vo); if (!map.isEmpty()) {
	 *           lErrEmail.setText((String) map.get("email")); } if
	 *           (!map.isEmpty()) { lErrPhone.setText((String)
	 *           map.get("phone"));
	 * 
	 *           } } });
	 * 
	 */

}