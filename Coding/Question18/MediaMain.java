package Question18;

public class MediaMain {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");       // Playing MP3 file. Name: song.mp3
        audioPlayer.play("mp4", "video.mp4");      // Playing MP4 file. Name: video.mp4
        audioPlayer.play("vlc", "movie.vlc");      // Playing VLC file. Name: movie.vlc
        audioPlayer.play("avi", "clip.avi");       // Invalid media. avi format not supported.
    }
}
