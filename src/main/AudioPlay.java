package main;
import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class AudioPlay {
	Sequence sequence;
	Sequencer sequencer;
	boolean play = true;
	
	public AudioPlay(File midi) {
		try {
		    sequence = MidiSystem.getSequence(midi);
		    sequencer = MidiSystem.getSequencer();
		    if(sequencer == null) throw new IOException("未找到可用音訊!");
		    sequencer.open();
		    sequencer.setSequence(sequence);
		} catch(InvalidMidiDataException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(MidiUnavailableException e) {e.printStackTrace();}
	}
	
	public void playmidi(boolean loop) {
		sequencer.start();
		if(loop) sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		else sequencer.setLoopCount(0);
	}
	
	public void stopmidi() {
		sequencer.stop();
		sequencer.close();
	}
}