package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an audio player which has to 
 * manage the game music and the effects 
 * 
 */
public class AudioPlayer {
	
	public static int MENU = 0;
	public static int LEVEL = 1;
	public static int COMPLETED = 2;
	
	public static int GAMEOVER = 0;
	
	private int currentSongId;
	
	private List<Clip> soundTracks;
	private List<Clip> soundEffects;
	
	private boolean isMuted;
	
    /**
     * Builds the audio player initializing 
     * the arrays containing the wav files
     * 
     */
	public AudioPlayer(){
		this.isMuted = false;
		this.loadSoundTracks();
		this.loadSoundEffects();
		this.playSoundTrack(MENU);
	}
	
    /**
     * Loads the menu and the level soundtracks
     * 
     */
	private void loadSoundTracks(){
		String[] soundNames = {"menu", "level", "game_completed"};
		this.soundTracks = new ArrayList<>(soundNames.length);
		for (String soundName : soundNames) {
			this.soundTracks.add(getAudio(soundName));
		}
	}
	
    /**
     * Loads the item selection and game over sound effects
     * 
     */
	private void loadSoundEffects(){
		/*
		 * creates a string array with the names of the audio files
		 * and then populates the Clip array
		 */
		String[] soundNames = {"game_over"};
		this.soundEffects = new ArrayList<>(soundNames.length);
		for (String soundName : soundNames) {
			this.soundEffects.add(getAudio(soundName));
		}
	}
	
	/**
	 * Retrieves an audio file wrapping it into a Clip object
	 * 
	 * @param fileName
	 * 		the audio file name
	 * @return currentClip
	 * 		the Clip object with the audio file
	 */
	private Clip getAudio(String fileName) {
		URL url = getClass().getResource("/audio/" + fileName + ".wav");
		AudioInputStream currentAudio;
		try {
			currentAudio = AudioSystem.getAudioInputStream(url);
			Clip currentClip = AudioSystem.getClip();
			currentClip.open(currentAudio);
			return currentClip;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * switches between audio and mute state
	 */
	public void setMute() {
		this.isMuted = !isMuted;	
		for(Clip sound: soundTracks) {
			BooleanControl bControl = (BooleanControl) sound.getControl(BooleanControl.Type.MUTE);
			bControl.setValue(isMuted);
		}
	}
	 /**
	  * plays the given id relative soundtrack
	  * 
	  * @param sID
	  * 	the id of the soundtrack to be played
	  */
	public void playSoundTrack(int sID) {
		this.stopSoundTrack();
		this.currentSongId = sID;
		this.soundTracks.get(currentSongId).setMicrosecondPosition(0);
		this.soundTracks.get(currentSongId).loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * it stops the current playing soundtrack
	 * 
	 */
	public void stopSoundTrack() {
		this.soundTracks.get(currentSongId).stop();
	}
	
	 /**
	  * plays the given id relative sound effect
	  * 
	  * @param sID
	  * 	the id of the effect to be played
	  */
	public void playSoundEffects(int sID) {
		this.soundEffects.get(sID).setMicrosecondPosition(0);
		this.soundEffects.get(sID).start();
	}
}
