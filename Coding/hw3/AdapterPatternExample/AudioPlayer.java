public class AudioPlayer implements MediaPlayer {
   MediaAdapter mediaAdapter;

   @Override
   public void play(String audioType, String fileName) {
      // Built-in support for playing mp3 music files
      if (audioType.equalsIgnoreCase("mp3")) {
         System.out.println("Playing mp3 file. Name: " + fileName);
      }
      // MediaAdapter is used to play other formats
      else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
         mediaAdapter = new MediaAdapter(audioType);
         mediaAdapter.play(audioType, fileName);
      } else {
         System.out.println("Invalid media. " + audioType + " format not supported");
      }
   }
}
