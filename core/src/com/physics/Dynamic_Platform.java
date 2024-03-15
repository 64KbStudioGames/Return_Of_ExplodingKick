package com.physics;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Dynamic_Platform {
	public Texture tx_dp;	
	SpriteBatch sb_dp;
	Vector2 dp_position;
	Vector2 dp_size;
	Boolean is_being_stepped;
	    
	    public Dynamic_Platform(Vector2 d_platform_position, Vector2 d_platform_size)
	    {	    	
	       sb_dp = new SpriteBatch();		
	       tx_dp= new Texture(Gdx.files.internal("Game_Art/dynamic_platform.png"));
	       dp_size = d_platform_size;
	       dp_position = d_platform_position;
	       is_being_stepped = false;
	    }	
	    
	
	    public void Draw_Dp(OrthographicCamera dp_camera)
	    {		
	        sb_dp.enableBlending();
			sb_dp.begin(); 
			sb_dp.setProjectionMatrix(dp_camera.combined);
			sb_dp.draw(tx_dp, dp_position.x-dp_size.x/2, dp_position.y-dp_size.y/2,dp_size.x,dp_size.y);	
			sb_dp.end();		
		
	    }
	    
}
