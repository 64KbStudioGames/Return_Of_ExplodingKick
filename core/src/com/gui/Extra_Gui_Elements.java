package com.gui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Extra_Gui_Elements 
{
	Camera cam;
	private Texture tx_stage_clear_background;
	Sprite sp_stage_clear_background;
	SpriteBatch sb_stage_clear_background;
	Vector2 size_stage_clear_background, position_stage_clear_background;
	public Extra_Gui_Elements(Camera _cam, Vector2 _position, Vector2 _size)
	{
		this.cam = _cam;		
		size_stage_clear_background = _size;
		position_stage_clear_background = _position;
		SetStageClearBackground();
	}
	public void SetStageClearBackground()
	{	
	tx_stage_clear_background = new Texture("Game_Art/stage_clear_background.png");
	sp_stage_clear_background = new Sprite(tx_stage_clear_background);
	sp_stage_clear_background.setSize(size_stage_clear_background.x, size_stage_clear_background.y);
	sp_stage_clear_background.setPosition(position_stage_clear_background.x, position_stage_clear_background.y);
	sb_stage_clear_background = new SpriteBatch();		
	}
	
	public void DrawStageClearBackground()
	{
      sb_stage_clear_background.setProjectionMatrix(cam.combined);
	  sb_stage_clear_background.begin();
	  sp_stage_clear_background.draw(sb_stage_clear_background);
	  sb_stage_clear_background.end();	 
	}
	public void DisposeEverything()
	{
		tx_stage_clear_background.dispose();		
	}
}
