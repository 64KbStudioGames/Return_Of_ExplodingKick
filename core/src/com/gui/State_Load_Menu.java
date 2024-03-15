package com.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.logic.GameStarter;

public class State_Load_Menu implements Screen 
{
	GameStarter game_starter;
 	// Set the Game world and the camera
	OrthographicCamera m_camera;
    public static float GAME_WORLD_WIDTH =  640;
	public static float GAME_WORLD_HEIGHT=   480;

	Viewport _viewport;	
	Texture tx_about;
	Sprite sp_about;
	SpriteBatch batch_about;	  
	
	public State_Load_Menu(GameStarter _game_starter)
	{
		this.game_starter = _game_starter;
	}

	@Override
	public void show() {
		m_camera = new OrthographicCamera();	
		_viewport = new StretchViewport((GAME_WORLD_WIDTH),(GAME_WORLD_HEIGHT),m_camera);	  
		_viewport.apply();
		
		 tx_about = new Texture(Gdx.files.internal("Game_Art/about_power_slime.png"));
		    sp_about = new Sprite(tx_about);
		    sp_about.setPosition(_viewport.getCamera().position.x-_viewport.getWorldWidth()/2, 
		    		_viewport.getCamera().position.y - _viewport.getWorldHeight()/2);
		    sp_about.setSize(_viewport.getWorldWidth(),_viewport.getWorldHeight());
		    batch_about = new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		m_camera.update(); 
		 batch_about.setProjectionMatrix(_viewport.getCamera().combined);
		   batch_about.begin();
		   batch_about.enableBlending();
		   sp_about.draw(batch_about);
		   batch_about.end();
		   
		   Gdx.input.setInputProcessor(Gdx.input.getInputProcessor());
		   if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
		   {
			   disposeEverything();
			   game_starter.setScreen(game_starter.start_menu);
		   }
	}

	private void disposeEverything()
	{
		tx_about.dispose();
		batch_about.dispose();
	}
	@Override
	public void resize(int width, int height) {
		_viewport.update(width, height);	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
