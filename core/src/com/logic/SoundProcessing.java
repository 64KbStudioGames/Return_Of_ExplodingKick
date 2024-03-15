package com.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundProcessing
{
	Sound sfx_jump, sfx_release;
	long id_sfx_jump, id_sfx_release;
	int switch_sfx_jump, switch_sfx_release;
	
	public SoundProcessing()
	{
		id_sfx_jump = 0;
		id_sfx_release = 0;
		switch_sfx_jump = 0;
		switch_sfx_release = 0;
		sfx_jump = Gdx.audio.newSound(Gdx.files.internal("Audio/jump.wav"));
		sfx_release = Gdx.audio.newSound(Gdx.files.internal("Audio/release_fall.wav"));
	}
	public void PlaySoundFx(char _sfx_type)
	{
		if(_sfx_type=='j')
		{
			switch_sfx_jump++;
			if(switch_sfx_jump ==1)
			id_sfx_jump = sfx_jump.play(0.625f);
		}
		if(_sfx_type =='r')
		{
			switch_sfx_release++;
			if(switch_sfx_release ==1)
			id_sfx_release = sfx_release.play();
			sfx_release.setVolume(id_sfx_release, 0.425f);
		}
	}	
	public void ResetFx()
	{	
		switch_sfx_jump = 0;			
		switch_sfx_release = 0;
	}
	public void DisposeEverything()
	{
		sfx_jump.dispose();
		sfx_release.dispose();
	}
}
