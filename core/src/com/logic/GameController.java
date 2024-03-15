//Game Controller Class
package com.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.xkick.Main_Unit;

public class GameController implements ControllerListener
	{
	   //Right Button...	
	   public Texture tx_right;
	   Button button_right;
	   public Boolean is_button_right_clicked;	  
	   //Left Button..
	   public Texture tx_left;
	   Button button_left;
	   public Boolean is_button_left_clicked;      
       //Jump Button
       public Texture tx_jump;
       Button button_jump;
       public Boolean is_button_jump_pressed;      
       //Kick Button
       public Texture tx_kick;
       Button button_kick;
       public Boolean is_button_kick_pressed,
       is_right_arrow_pressed, is_left_arrow_pressed, is_up_arrow_pressed, is_down_arrow_pressed;    
       //Stage...
	   public Stage stage_controller;
       public StretchViewport game_controller_viewport;
      public  OrthographicCamera game_controller_camera;             
       //extras
       public  Boolean d_kick;
       private Boolean jump_with_button, is_dpad_active;
       private int switch_jump_with_button;
       
	   public GameController (float _game_world_width, float _game_world_height) 
	   {	    
		    game_controller_viewport = new StretchViewport(_game_world_width, _game_world_height);
		    this.game_controller_camera = new OrthographicCamera();
		    game_controller_viewport.setCamera(game_controller_camera);		    
		    game_controller_viewport.apply(true);		 
		    
		    this.stage_controller = new Stage();		

		    button_jump      = new Button();
		
			is_button_left_clicked  = false;
			is_button_right_clicked = false;
			is_button_jump_pressed  = false;
		    is_button_kick_pressed  = false;
		    is_up_arrow_pressed = false;
		    is_down_arrow_pressed = false;
		
		    //right button
				tx_right 	   = new Texture(Gdx.files.internal("Game_Controllers/right_arrow.png"));
				tx_right.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
				button_right     = new Button();
				Drawable drawable_right_up = new TextureRegionDrawable(tx_right);
				ButtonStyle button_style_right = new ButtonStyle();
				button_style_right.down = drawable_right_up;
				button_style_right.up = drawable_right_up;
				button_right.setStyle(button_style_right);
				button_right.setPosition(160, game_controller_camera.position.y-game_controller_viewport.getWorldHeight()/1.85f);
				button_right.setSize(150, 250);				
			//left button	
				tx_left  	   = new Texture(Gdx.files.internal("Game_Controllers/left_arrow.png"));			
				button_left = new Button();
				Drawable drawable_left_up = new TextureRegionDrawable(tx_left);
				ButtonStyle button_style_left = new ButtonStyle();
				button_style_left.up = drawable_left_up;
				button_style_left.down = drawable_left_up;
				button_left.setStyle(button_style_left);
				button_left.setPosition(button_right.getX()-200, button_right.getY());
				button_left.setSize(button_right.getWidth(), button_right.getHeight());			
				//kick button
				tx_kick  	   = new Texture(Gdx.files.internal("Game_Controllers/Fire_button.png"));		
				button_kick = new Button();
				Drawable drawable_kick_up = new TextureRegionDrawable(tx_kick);
				ButtonStyle button_style_kick = new ButtonStyle();
				button_style_kick.up = drawable_kick_up;
				button_style_kick.down = drawable_kick_up;
				button_kick.setStyle(button_style_kick);
				button_kick.setPosition(game_controller_camera.position.x + _game_world_width/8, button_right.getY()+32.5f);
				button_kick.setSize(150,150);
				//jump button
				tx_jump  	   = new Texture(Gdx.files.internal("Game_Controllers/Jump_button.png"));		
				button_jump = new Button();
				Drawable drawable_jump_up = new TextureRegionDrawable(tx_jump);
				ButtonStyle button_style_jump = new ButtonStyle();
				button_style_jump.up = drawable_jump_up;
				button_style_jump.down = drawable_jump_up;
				button_jump.setStyle(button_style_jump);
				button_jump.setPosition(button_kick.getX() + 200, button_kick.getY());
				button_jump.setSize(button_kick.getWidth(), button_kick.getHeight());			  
		      stage_controller.setViewport(game_controller_viewport);		 
		      stage_controller.addActor(button_left);
		      stage_controller.addActor(button_right);
		      stage_controller.addActor(button_jump);
		      stage_controller.addActor(button_kick);		
		     
		    button_right.addListener(new InputListener(){ 
		    	@Override
		    	public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button)
			{
				is_button_right_clicked = true;
				is_button_left_clicked = false;			
			    return is_button_right_clicked;
			}	
		       	@Override
		    	public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button)
			{
				is_button_right_clicked = false;			    
			}			
		    });	 
		    
		    button_left.addListener(new InputListener(){ 
		    	@Override
			    public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button)
				{
					is_button_left_clicked = true;
					is_button_right_clicked = false;			
				    return is_button_left_clicked;
				}		
		    	@Override
			    public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button)
				{
					is_button_left_clicked = false;					
				}	
			    });			    
		    	button_jump.addListener(new InputListener(){ 
		    	@Override
			    public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button)
				{		    					
					is_button_jump_pressed = true;					
				    return is_button_jump_pressed;
				 }	
		    	
		    	@Override
			    public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button)
				{
		    		
					is_button_jump_pressed = false;	
					
				}	
			    });			    
		    //button click code
		    button_kick.addListener(new ClickListener()
			{
			@Override
			public void clicked(InputEvent event, float x, float y) {				
	    	   				    			
	    			is_button_kick_pressed = true;    		
	    		}			
			});						
			//Physical Joypad Controller			
			Controllers.addListener(this);			
			d_kick = false;
			jump_with_button = true;
			switch_jump_with_button =0;
			is_dpad_active = false;
	   }	
		
   public void Draw_joy_pad (Boolean _is_on_pc) 
   {
	   //you must definetely bring back this line...
	if(Gdx.app.getType().toString().equals("Desktop") || Gdx.app.getType().toString().equals("Html"))
	   Hardware_JoyPad_Moves(); 
 
	   if(!_is_on_pc)
	  {		
		   stage_controller.draw();
		   stage_controller.act();	
		   Gdx.input.setInputProcessor(stage_controller);	  
	   if(Main_Unit.g_controller_type.equals("JoyPad"))	
		   is_button_kick_pressed = false; 	   
	  }    
   }
   private void Hardware_JoyPad_Moves()
   {	 
	 try {
	if(!is_dpad_active)
	{
	    if(Controllers.getControllers().get(0).getAxis(0)<-0.1f)
	    {
	    	is_button_left_clicked = true;
	    	is_button_right_clicked = false;
	    }
	    if(Controllers.getControllers().get(0).getAxis(0)>0.1f)	    	
	    {
	    	is_button_right_clicked = true;
	    	is_button_left_clicked = false;
	    }
	    if(Controllers.getControllers().get(0).getAxis(0)>-0.1f && Controllers.getControllers().get(0).getAxis(0)<0.05f)
	    {
	    	is_button_right_clicked = false;
	    	is_button_left_clicked = false;
	    }	 
	}
	    if(!jump_with_button)
	    {
	    if(Controllers.getControllers().get(0).getAxis(1)<-0.1f)
	    	is_button_jump_pressed = true;	    
	    if(Controllers.getControllers().get(0).getAxis(1)>-0.05f)	    
	    	is_button_jump_pressed = false;	   
	    }
	    
	   if(Controllers.getControllers().get(0).getButton(2))
		   is_button_kick_pressed = true;
	   is_button_kick_pressed = false;
	 }
	 catch(Exception ex)
	 {
		//System.out.println("JoyPad Disconnected");
	 }	  
   }
   public Boolean IsAnyHardwareKeyPressed()
   {
	   Boolean result= false;
	   if(!is_button_jump_pressed && !is_button_right_clicked && !is_button_left_clicked)
		   result = false;	 
	   if(is_button_jump_pressed || is_button_right_clicked || is_button_left_clicked)
		   result = true;
	   return result;
   }
public static void VibrateJoypad()
{	
	//Controllers.getControllers().get(0).startVibration(800,0.885f);
}
public void Menu_Selections_With_Physical_Joypad()
{
	  //up and down control for the menus
	   if(Controllers.getControllers().get(0).getAxis(1)>0.1f)
	    {
	    	is_down_arrow_pressed = true;
	    	is_up_arrow_pressed = false;
	    	System.out.println("down arrow pressed");
	    }
	    if(Controllers.getControllers().get(0).getAxis(1)<-0.1f)
	    {
	    	is_down_arrow_pressed = false;
	    	is_up_arrow_pressed = true;
	    	System.out.println("up arrow pressed");
	    }
}
@Override
public void connected(Controller controller) 
{
	System.out.println("joyad_connected");
}

@Override
public void disconnected(Controller controller) 
{
	//System.out.println("joyad_disconnected");
}

@Override
public boolean buttonDown(Controller controller, int buttonCode) {	
	if(buttonCode ==0 && jump_with_button)	
		is_button_jump_pressed = true;	
	if(buttonCode == 2)
		d_kick = true;
	if(buttonCode==4)
	{
		switch_jump_with_button++;
		if(switch_jump_with_button%2==0)
		jump_with_button = true;
		else 
			jump_with_button = false;
	}
	if((buttonCode ==14 || buttonCode == 13)) 
	{
		is_dpad_active = true;
		if(buttonCode == 14)
		{
			is_button_right_clicked = true;
			is_button_left_clicked = false;
		}
		if(buttonCode == 13)
		{
			is_button_left_clicked = true;
			is_button_right_clicked = false;
		}
	}
		//is_dpad_active = false;
	// up: 11, right: 14, left: 13	
	return false;
}

@Override
public boolean buttonUp(Controller controller, int buttonCode) {
	if(buttonCode ==0 && jump_with_button)	
		is_button_jump_pressed = false;	
	if(buttonCode ==2)
		d_kick = false;

	if(buttonCode==14)
			is_button_right_clicked = false;
	if(buttonCode==13)
			is_button_left_clicked = false;
	if(buttonCode ==14 && buttonCode ==13 )
			is_dpad_active = false;

	return false;
}

@Override
public boolean axisMoved(Controller controller, int axisCode, float value) 
{
	if(Math.abs(value)>0.5f)
		is_dpad_active = false;
	return false;
}	

}
