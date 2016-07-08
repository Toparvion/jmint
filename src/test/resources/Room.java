package websocket.drawboard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import websocket.drawboard.wsmessages.BinaryWebsocketMessage;
import websocket.drawboard.wsmessages.StringWebsocketMessage;

/**
 * A Room represents a drawboard where a number of
 * users participate.<br><br>
 *
 * Note: Instance methods should only be invoked by calling
 * {@link #invokeAndWait(Runnable)} to ensure access is correctly synchronized.
 */
public final class Room {

    /**
     * The current image of the room drawboard. DrawMessages that are
     * received from Players will be drawn onto this image.
     */
    private final BufferedImage roomImage =
            new BufferedImage(900, 600, BufferedImage.TYPE_INT_RGB);
    private final Graphics2D roomGraphics = roomImage.createGraphics();

  /**
   * @cutpoint AFTER
   */
  public Room() {
        roomGraphics.setBackground(Color.ORANGE);
        roomGraphics.clearRect(0, 0, roomImage.getWidth(),
                roomImage.getHeight());
    }

    /**
     * A Player participates in a Room. It is the interface between the
     * {@link Room} and the {@link Client}.<br><br>
     *
     * Note: This means a player object is actually a join between Room and
     * Client.
     */
    public final class Player {

        /**
         * The room to which this player belongs.
         */
        private Room room;

        private final Client client;

        /**
         * Handles the given DrawMessage by drawing it onto this Room's
         * image and by broadcasting it to the connected players.
         * @param msg
         * @param msgId
         * @cutpoint BEFORE
         */
        public void handleDrawMessage(DrawMessage msg, long msgId) {
            System.out.println("Draw message is handled with type " + msg.getType() +
                " and message id=" + msgId);
        }


    }
}
