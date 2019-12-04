package vlcj;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;


    public class MediaPanel {

        public static void main(final String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                     chargerLibrairie();
                     new MediaPanel(args);
                }
            });
        }

        static void chargerLibrairie(){
             NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            LibXUtil.initialise();
        }
       
        private MediaPanel(String[] args) {
            JFrame frame = new JFrame("Tutoriel vlcj");
            frame.setLocation(100, 100);
            frame.setSize(1050, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            Canvas c = new Canvas();
            c.setBackground(Color.black);
            JPanel p = new JPanel();
            p.setLayout(new BorderLayout());
            p.add(c, BorderLayout.CENTER);
            frame.add(p, BorderLayout.CENTER);
            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
            EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(frame));
            mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
            mediaPlayer.toggleFullScreen();
            mediaPlayer.setEnableMouseInputHandling(false);
            mediaPlayer.setEnableKeyInputHandling(true);
            mediaPlayer.prepareMedia("C:\\Users\\Alejandro\\Desktop\\Cosas\\Videos\\video.mp4");
            mediaPlayer.play();
        }
    }