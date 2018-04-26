package de.mth.game.common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class Input implements KeyListener, MouseMotionListener, MouseListener {

	private static final Input input = new Input();

	private boolean w;
	private long timeW;
	private boolean s;
	private boolean a;
	private boolean d;

	private boolean mousePressed = false;
	private long pressedTime;
	private int pressedX;
	private int pressedY;

	private boolean mouseReleased = false;
	private int releasedX;
	private int releasedY;

	private boolean mouseDragged = false;
	private int draggedX;
	private int draggedY;

	private int mouseX;
	private int mouseY;

	private boolean leftMousePressed = false;
	private boolean middleMousePressed = false;
	private boolean rightMousePressed = false;
	private boolean leftMouseReleased = false;
	private boolean middleMouseReleased = false;
	private boolean rightMouseReleased = false;
	private boolean leftMouseDragged = false;
	private boolean middleMouseDragged = false;
	private boolean rightMouseDragged = false;

	public boolean toggleD = false;

	private Input() {
	}

	public static Input getInstance() {
		return input;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setDraggedX(e.getX());
		setDraggedY(e.getY());
		setMouseX(e.getX());
		setMouseY(e.getY());
		setMouseDragged(true);
		// System.out.println("Dragged");
		if (SwingUtilities.isLeftMouseButton(e)) {
			// System.out.println("LeftMouseDragged");
			setLeftMouseDragged(true);
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			// System.out.println("RightMouseDragged");
			setRightMouseDragged(true);
		}
		if (SwingUtilities.isMiddleMouseButton(e)) {
			// System.out.println("MiddleMouseDragged");
			setMiddleMouseDragged(true);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println("moved");
		setMouseX(e.getX());
		setMouseY(e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println("Enter");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println("Exit");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("pressed");
		setMouseReleased(false);
		setPressedX(e.getX());
		setPressedY(e.getY());
		setMousePressed(true);
		setMiddleMouseReleased(false);
		setRightMouseReleased(false);

		setLeftMouseReleased(false);

		setLeftMouseReleased(false);
		if (SwingUtilities.isLeftMouseButton(e)) {
			// System.out.println("LeftMousePressed");
			setLeftMousePressed(true);
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			// System.out.println("RightMousePressed");
			setRightMousePressed(true);
		}
		if (SwingUtilities.isMiddleMouseButton(e)) {
			// System.out.println("MiddleMousePressed");
			setMiddleMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("released");
		setMousePressed(false);
		setMouseDragged(false);
		setReleasedX(e.getX());
		setReleasedY(e.getY());
		setMouseReleased(true);
		setLeftMouseDragged(false);
		setLeftMousePressed(false);
		setRightMouseDragged(false);
		setRightMousePressed(false);
		setMiddleMouseDragged(false);
		setMiddleMousePressed(false);

		if (SwingUtilities.isLeftMouseButton(e)) {
			System.out.println("LeftMouseReleased");
			setLeftMouseReleased(true);
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			System.out.println("RightMouseReleased");
			setRightMouseReleased(true);
		}
		if (SwingUtilities.isMiddleMouseButton(e)) {
			// System.out.println("MiddleMouseReleased");
			setMiddleMouseReleased(true);
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D)
			setD(true);
		if (key == KeyEvent.VK_A)
			setA(true);
		if (key == KeyEvent.VK_S)
			setS(true);
		if (key == KeyEvent.VK_W)
			setW(true);

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {
			setD(false);
			// Debug
			toggleD = !toggleD;
		}
		if (key == KeyEvent.VK_A)
			setA(false);
		if (key == KeyEvent.VK_S)
			setS(false);
		if (key == KeyEvent.VK_W)
			setW(false);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public boolean isW() {
		return w;
	}

	public void setW(boolean w) {
		this.w = w;
	}

	public boolean isS() {
		return s;
	}

	public boolean isMouseDragged() {
		return mouseDragged;
	}

	public void setMouseDragged(boolean mouseDragged) {
		this.mouseDragged = mouseDragged;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	public boolean isA() {
		return a;
	}

	public void setA(boolean a) {
		this.a = a;
	}

	public boolean isD() {
		return d;
	}

	public void setD(boolean d) {
		this.d = d;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public boolean isMouseReleased() {
		return mouseReleased;
	}

	public void setMouseReleased(boolean mouseReleased) {
		this.mouseReleased = mouseReleased;
	}

	public int getPressedX() {
		return pressedX;
	}

	public void setPressedX(int pressedX) {
		this.pressedX = pressedX;
	}

	public int getPressedY() {
		return pressedY;
	}

	public void setPressedY(int pressedY) {
		this.pressedY = pressedY;
	}

	public int getReleasedX() {
		return releasedX;
	}

	public void setReleasedX(int releasedX) {
		this.releasedX = releasedX;
	}

	public int getReleasedY() {
		return releasedY;
	}

	public void setReleasedY(int releasedY) {
		this.releasedY = releasedY;
	}

	public int getDraggedX() {
		return draggedX;
	}

	public void setDraggedX(int draggedX) {
		this.draggedX = draggedX;
	}

	public int getDraggedY() {
		return draggedY;
	}

	public void setDraggedY(int draggedY) {
		this.draggedY = draggedY;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public long getPressedTime() {
		return pressedTime;
	}

	public void setPressedTime(long pressedTime) {
		this.pressedTime = pressedTime;
	}

	public long getTimeW() {
		return timeW;
	}

	public void setTimeW(long timeW) {
		this.timeW = timeW;
	}

	public boolean isLeftMousePressed() {
		return leftMousePressed;
	}

	public void setLeftMousePressed(boolean leftMousePressed) {
		this.leftMousePressed = leftMousePressed;
	}

	public boolean isMiddleMousePressed() {
		return middleMousePressed;
	}

	public void setMiddleMousePressed(boolean middleMousePressed) {
		this.middleMousePressed = middleMousePressed;
	}

	public boolean isRightMousePressed() {
		return rightMousePressed;
	}

	public void setRightMousePressed(boolean rightMousePressed) {
		this.rightMousePressed = rightMousePressed;
	}

	public boolean isLeftMouseReleased() {
		return leftMouseReleased;
	}

	public void setLeftMouseReleased(boolean leftMouseReleased) {
		this.leftMouseReleased = leftMouseReleased;
	}

	public boolean isMiddleMouseReleased() {
		return middleMouseReleased;
	}

	public void setMiddleMouseReleased(boolean middleMouseReleased) {
		this.middleMouseReleased = middleMouseReleased;
	}

	public boolean isRightMouseReleased() {
		return rightMouseReleased;
	}

	public void setRightMouseReleased(boolean rightMouseReleased) {
		this.rightMouseReleased = rightMouseReleased;
	}

	public boolean isLeftMouseDragged() {
		return leftMouseDragged;
	}

	public void setLeftMouseDragged(boolean leftMouseDragged) {
		this.leftMouseDragged = leftMouseDragged;
	}

	public boolean isMiddleMouseDragged() {
		return middleMouseDragged;
	}

	public void setMiddleMouseDragged(boolean middleMouseDragged) {
		this.middleMouseDragged = middleMouseDragged;
	}

	public boolean isRightMouseDragged() {
		return rightMouseDragged;
	}

	public void setRightMouseDragged(boolean rightMouseDragged) {
		this.rightMouseDragged = rightMouseDragged;
	}

	public boolean isToggleD() {
		return toggleD;
	}

	public void setToggleD(boolean toggleD) {
		this.toggleD = toggleD;
	}

}
