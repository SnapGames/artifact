package com.snapgames.framework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

import com.neet.artifact.game.gamestate.ArtifactGameStateManager;
import com.snapgames.framework.handler.InputHandler;
import com.snapgames.framework.state.GSM;

/**
 * Game Panel implementation.
 *
 * @author ForeignGuyMike
 * @author Frédéric Delorme
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

    // dimensions
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;
    // game state manager
    protected GSM gsm;
    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    // image
    private BufferedImage image;
    private Graphics2D g;
    /**
     * Record generated image to timeline screenshot.
     */
    private boolean recording = false;
    private int recordingCount = 0;
    /**
     * take a screen shot of the game.
     */
    private boolean screenShot;
    /**
     * Switch to full screen mode.
     */
    // TODO private boolean fullScreen;

    /**
     * Default constructor for this Game. Initialize object size.
     */
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.JComponent#addNotify()
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    /**
     * Initialize the game.
     */
    public void init() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        running = true;

        gsm = new ArtifactGameStateManager();

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    public void run() {
        init();

        long start = 0;
        long elapsed = 0;
        long wait = 0;

        // game loop
        while (running) {

            start = System.nanoTime();

            update(elapsed);
            draw(g);
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;

            if (wait < 0)
                wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Update Game state.
     *
     * @param deltaTime
     */
    private void update(long deltaTime) {
        gsm.update(deltaTime);
        InputHandler.update(deltaTime);
    }

    private void draw(Graphics2D g) {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
        if (screenShot) {
            screenShot = false;
            try {
                java.io.File out = new java.io.File("screenshot "
                        + System.nanoTime() + ".gif");
                javax.imageio.ImageIO.write(image, "gif", out);
            } catch (Exception e) {
            }
        }
        if (!recording)
            return;
        try {
            String path = System.getProperty("user.dir");
            java.io.File out = new java.io.File(path + File.separator + "frame"
                    + recordingCount + ".gif");
            javax.imageio.ImageIO.write(image, "JPG", out);
            recordingCount++;
        } catch (Exception e) {
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped(KeyEvent key) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(KeyEvent key) {
        if (key.isControlDown()) {
            if (key.getKeyCode() == KeyEvent.VK_R) {
                recording = !recording;
                return;
            }
            if (key.getKeyCode() == KeyEvent.VK_S) {
                screenShot = true;
                return;
            }
        }
        InputHandler.keySet(key.getKeyCode(), true);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased(KeyEvent key) {
        InputHandler.keySet(key.getKeyCode(), false);
    }

}
