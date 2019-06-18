package com.dyga.Engine.Source.MVC.Controler;

import com.dyga.Engine.Source.MVC.View.MainView;

import com.dyga.Engine.Source.MVC.Model.MainModel;
import com.dyga.Engine.Source.MVC.View.Menu.Component.ViewButton;
import com.dyga.Engine.Source.Utils.Tuple;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

public class MainControler implements KeyListener, MouseListener, ActionListener  {

	// NEW META
	private MainView mainView;
	private MainModel mainModel;

	private static String INPUT_PATH = "Scripts.Inputs.ButtonsCallback"; // Temporary

	public static Dictionary<String, Tuple<Class, Method>> controlerMethods;

	// END NEW META

	private boolean actionCreuser;
	private List<ActionListener> actionsListener;

	/**
	 * Basic constructor
	 */
	public MainControler() {
		controlerMethods = new Hashtable<>();
		actionsListener = new LinkedList<>();
	}

	public void init(MainModel mainModel) {
		this.mainModel = mainModel;
		LoadGameScripts();
	}

	public void addView(MainView view) {
		this.mainView = view;
	}

	public void notifyView() {
		// TODO
		//this.view.notifyChanges();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.toString());
		System.out.println("mouseClicked " + e.getButton() + ", " + e.getComponent() + " !");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed " + e.getButton() + " !");
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered " + e.toString() + " !");
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Called the action Performed of the Source.MVC.Controler " + e.toString());

		System.out.println("[DEBUG] menu = " + ((ViewButton)e.getSource()).getListenersMap().toString());

		Enumeration<String> enumaration = ((ViewButton)e.getSource()).getListenersMap().elements();
		while(enumaration.hasMoreElements()) {
			String key = enumaration.nextElement();
			System.out.println(key);

			if(this.controlerMethods.get(key) != null) {
				System.out.println(((Method)this.controlerMethods.get(key).second).getName());
				try {
					// Debug
					System.out.println(((Method)this.controlerMethods.get(key).second).getName());
					System.out.println(((Class)this.controlerMethods.get(key).first).getDeclaredConstructor().newInstance());

					// Get the method that we need to call
					Method callbackMethod = ((Method)this.controlerMethods.get(key).second);
					// Get the class we need to call the method on
					Class callbackClass = this.controlerMethods.get(key).first;
					// Call it !
					callbackMethod.invoke(callbackClass.getDeclaredConstructor().newInstance(), (ViewButton)e.getSource());

					break;
				} catch (InstantiationException ex) {
					ex.printStackTrace();
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				} catch (InvocationTargetException ex) {
					ex.printStackTrace();
				} catch (NoSuchMethodException ex) {
					ex.printStackTrace();
				}
			}
		}

		// TODO REVIEW THAT
		// Notify the view that it needs to repaint.
		//this.mainView.paint(0,0);


		// OLD VERSION
		//test.get(((ContextTransitionButton)e.getSource()).getButtonName()).actionPerformed(e);
	}

	/**####################### Views Function #######################*/

	private Dictionary<String, ActionListener> test = new Hashtable<>();

	public void ChangeView(String newView) {
		ChangeView(newView, false);
	}

	public void ChangeView(String newView, boolean isBackButton) {
		System.out.println("[Controller function] Context transition : NewView = " + newView);
		// Update the model
		// ??????
		//this.model.ChangeView(newView, isBackButton);

		// Notify the view that it needs to update
		//this.view.refreshView();
	}

	/*
	public void returnLastView() {
		System.out.println("[Controller function] Context transition : Return to last view");
		// Update the model
		this.model.ReturnLastView();

		// Notify the view that it needs to update
		//this.view.refreshView();
	}*/
	/*
	public void HandleActionParams(StandardButton button) {
		System.out.println("[Controller function] Action with parameters");

		this.model.Update();
		button.getStandardImage();

	}*/

	public void changeView(String nextView) {
		System.out.println("[Controller function] Context transition : Return to last view");

		// Update the model
		this.mainModel.updateView(nextView);

		// TODO REVIEW THAT
		// Notify the view
		//this.mainView.paint(0, 0);
	}

	public void returnLastView() {
		System.out.println("[Controller function] Context transition : Return to last view");

		// Update the model
		this.mainModel.returnLastView();

		// TODO REVIEW THAT
		// Notify the view
		//this.mainView.paint(0,0);
	}



	public static void LoadGameScripts() {
		//ClassLoader classLoader = MainControler.class.getClassLoader();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		/*
		try {
			Class aClass = classLoader.loadClass("GameCode");
			System.out.println("aClass.getName() = " + aClass.getName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}*/

		/*
		assert classLoader != null;
		//String path = new String(INPUTS_FOLDER_NAME).replace('.', '/');
		Enumeration resources = null;
		try {
			resources = classLoader.getResources("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<File> dirs = new ArrayList();
		while (resources.hasMoreElements()) {
			URL resource = (URL) resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}*/

		ArrayList<Class> classes = new ArrayList();
		// TODO : Find a better solution for that..
		try {
			classes.add(Class.forName(INPUT_PATH));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/*
		for (File directory : dirs) {
			try {
				//classes.addAll(findClasses(directory, INPUTS_FOLDER_NAME));
				// TODO : Find a better solution for that..
				classes.add(Class.forName(INPUT_PATH));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}*/

		for(Class currentClass : classes.toArray(new Class[classes.size()])) {
			System.out.println("[HERE] " + currentClass.getName());

			// Add all the methods for that class
			for (Method method : currentClass.getDeclaredMethods()) {
				String key = currentClass.getName() + "." + method.getName();
				System.out.println("[DEBUG] Added to the methods dicts = <" + key + ", " + currentClass.getName() + ">");
				controlerMethods.put(key, new Tuple<>(currentClass, method));
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	/*/
	private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
		List classes = new ArrayList();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			System.out.println(file.getName().contains(packageName));
			if(file.getName().contains(packageName)) {
				if (file.isDirectory()) {
					assert !file.getName().contains(".");
					classes.addAll(findClasses(file, ""));
				} else if (file.getName().endsWith(".class")) {
					classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				}
			}
		}
		return classes;
	}*/

	/**####################### End Views Function #######################*/
}
