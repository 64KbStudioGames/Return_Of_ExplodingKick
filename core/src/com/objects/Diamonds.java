package com.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Diamonds 
{

	public Vector2 diamond_pos;
	Texture diamond_tx;
	SpriteBatch diamond_sprite_batch;
	float diamond_width;
	float diamond_height;
	Rectangle rect;

	public Diamonds(Vector2 Diamond_position, SpriteBatch Diamond_sprite_batch, Texture Diamond_texture, float Diamond_Width, float Diamond_Height)
	{
		this.diamond_pos = Diamond_position;
		this.diamond_tx = Diamond_texture;
		this.diamond_sprite_batch = Diamond_sprite_batch;
		this.diamond_width = Diamond_Width;
		this.diamond_height = Diamond_Height;
    
	}

	public void Draw_Diamond(OrthographicCamera Diamond_camera)
	{
		diamond_sprite_batch.enableBlending();
	    diamond_sprite_batch.begin();
	    diamond_sprite_batch.setProjectionMatrix(Diamond_camera.combined);
	    diamond_sprite_batch.draw(diamond_tx, diamond_pos.x-30/100f, diamond_pos.y-18f/100f,70/100f,70/100f);	
	    diamond_sprite_batch.end();

	}



}