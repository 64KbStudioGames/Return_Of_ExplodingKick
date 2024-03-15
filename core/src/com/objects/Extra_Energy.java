package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Extra_Energy 
{
	Vector2 heart_position;
	public SpriteBatch sb_heart;
	public Texture tx_heart;
	public Boolean heart_taken;
	OrthographicCamera camera_heart;
	public Boolean heart_already_collected;
	
	public Extra_Energy(Vector2 position_vector, OrthographicCamera heart_camera)
	{
		heart_position = position_vector;
	    sb_heart = new SpriteBatch();		
	    tx_heart=  new Texture(Gdx.files.internal("Game_Art/heart.png"));
	    camera_heart = heart_camera;
	    heart_taken = false;
	    heart_already_collected = false;
	}
	public void Draw_Heart()
	{
		sb_heart.enableBlending();
		sb_heart.begin(); 
		sb_heart.setProjectionMatrix(camera_heart.combined);
		sb_heart.draw(tx_heart, heart_position.x-30/100f, heart_position.y-10/100f,64/100f,64/100f);	
		sb_heart.end();		
	}
}
