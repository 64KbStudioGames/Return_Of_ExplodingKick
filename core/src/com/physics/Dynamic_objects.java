    package com.physics;

	import com.badlogic.gdx.graphics.OrthographicCamera;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.badlogic.gdx.math.Rectangle;
	import com.badlogic.gdx.math.Vector2;

	public class Dynamic_objects 
	{

		public Vector2 dynamic_object_position;
		Texture dynamic_object_tx;
		SpriteBatch dynamic_object_sprite_batch;
		float dynamic_object_width;
		float dynamic_object_height;
		Rectangle rect;

		public Dynamic_objects(Vector2 Dynamic_object_position, SpriteBatch Dynamic_object_sprite_batch, Texture Dynamic_object_texture, float Dynamic_object_width, float Dynamic_object_height)
		{
			this.dynamic_object_position = Dynamic_object_position;
		    this.dynamic_object_tx = Dynamic_object_texture;
			this.dynamic_object_sprite_batch = Dynamic_object_sprite_batch;
			this.dynamic_object_width = Dynamic_object_width;
			this.dynamic_object_height = Dynamic_object_height;
	    
		}

		public void Draw_Dynamic_Object(OrthographicCamera Dynamic_object_camera)
		{
			dynamic_object_sprite_batch.enableBlending();
		    dynamic_object_sprite_batch.begin();
		    dynamic_object_sprite_batch.setProjectionMatrix(Dynamic_object_camera.combined);
		    dynamic_object_sprite_batch.draw(dynamic_object_tx, dynamic_object_position.x-30/100f, dynamic_object_position.y-32/100f,64/100f,65/100f);	
		    dynamic_object_sprite_batch.end();

		}

	}


