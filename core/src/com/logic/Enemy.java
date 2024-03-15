package com.logic;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.gui.Enemy_UI;
import com.xkick.Main_Unit;

	public class Enemy 
	{
		public Vector2 enemy_position;
		public SpriteBatch enemy_batch;
		String enemy_type;
		public Boolean is_enemy_a_boss;
		int enemy_level;
		public Body enemy_body;
		Vector2 player_position;
		public float enemy_jump_power;
		public float enemy_speed;
		Boolean can_enemy_jump;
		OrthographicCamera e_camera;
		World e_world;
		ArrayList<Rectangle> rectangles_of_gravity;
		Rectangle feet_enemy;
		Box2DDebugRenderer b2d_debug_renderer;
		public double life;
		public Rectangle border_enemy;
		Rectangle border_character;
		//ShapeRenderer test_shape;
		Boolean can_kick;		
		public Boolean is_enemy_dead;	
		double distance_enemy_player;		
				//Animations
				//Starter of Animations			
				public Texture enemy_animation_spread_sheet;
				TextureRegion[] frames_enemy;										
				//Lost Animation				
				public Texture enemy_animation_spread_sheet_lost;
				TextureRegion[] frames_enemy_lost;	
				//Lost Right Animation
				Animation<TextureRegion>  enemy_animation_lost_right;
				TextureRegion[] frames_enemy_lost_right;
				float stateTime_lost_right;
				TextureRegion currentFrame_lost_right;
				//Lost Left Animaiton
				Animation<TextureRegion>  enemy_animation_lost_left;
				TextureRegion[] frames_enemy_lost_left;
				float stateTime_lost_left;
				TextureRegion currentFrame_lost_left;
			    //Right Kick Animation
				Animation<TextureRegion>  enemy_animation_right_kick;
				TextureRegion[] frames_enemy_right_kick;
				float stateTime_right_kick;
				TextureRegion currentFrame_right_kick;	
				//Alternative Right kick Animation;
				Animation<TextureRegion>  alt_enemy_animation_right_kick;
				TextureRegion[] alt_frames_enemy_right_kick;
				float alt_stateTime_right_kick;
				TextureRegion alt_currentFrame_right_kick;		   
			
				//Left Kick Animation
				Animation<TextureRegion>  enemy_animation_left_kick;
				TextureRegion[] frames_enemy_left_kick;
				float stateTime_left_kick;
				TextureRegion currentFrame_left_kick;				
				//Alternative Left Kick Animation
				Animation<TextureRegion> alt_enemy_animation_left_kick;
				TextureRegion[] alt_frames_enemy_left_kick;
				float alt_stateTime_left_kick;
				TextureRegion alt_currentFrame_left_kick;		
			    
				//Walking Right Animation
				Animation<TextureRegion> enemy_animation_walking_right;
				TextureRegion[] frames_enemy_walking_right;
				float stateTime_walking_right;
				TextureRegion currentFrame_walking_right;	
				//Standing Right Animation
				Animation<TextureRegion> enemy_animation_standing_right;
				TextureRegion[] frames_standing_right;
				float stateTime_standing_right;
				TextureRegion currentFrame_standing_right;
				//Walking Left Animation
				Animation<TextureRegion>  enemy_animation_walking_left;
				TextureRegion[] frames_enemy_walking_left;
				float stateTime_walking_left;
				TextureRegion currentFrame_walking_left;	
				//Standing Left Animation
				Animation<TextureRegion> enemy_animation_standing_left;
				TextureRegion[] frames_standing_left;
				float stateTime_standing_left;
				TextureRegion currentFrame_standing_left;
				//Getting Impact from Right Animation
				Animation<TextureRegion>  enemy_animation_getting_impact_from_right;
				TextureRegion[] frames_enemy_getting_impact_from_right;
				float stateTime_getting_impact_from_right;
				TextureRegion currentFrame_getting_impact_from_right;				
				//Getting Impact from Left Animation
				Animation<TextureRegion>  enemy_animation_getting_impact_from_left;
				TextureRegion[] frames_enemy_getting_impact_from_left;
				float stateTime_getting_impact_from_left;
				TextureRegion currentFrame_getting_impact_from_left;
				
				//Throw Shuriken Right Animation
				Animation<TextureRegion>  enemy_animation_throw_shuriken_right;
				TextureRegion[] frames_enemy_throw_shuriken_right;
				float stateTime_throw_shuriken_right;
				TextureRegion currentFrame_throw_shuriken_right;	
				//Throw Shuriken Left Animation
				Animation<TextureRegion>  enemy_animation_throw_shuriken_left;
				TextureRegion[] frames_enemy_throw_shuriken_left;
				float stateTime_throw_shuriken_left;
				TextureRegion currentFrame_throw_shuriken_left;
				
				//Define the movement
			    public String e_movement;
			    Boolean enable_animation;
			    float e_DeltaTime;		  
			    
			    //Define the advanced A.I variables
			    int e_kick_timer;
			    int e_kicking_time_limit;
		
		//Sound Controller	
		public Sound sound_fx_enemy_lost;
		int lost_sound_counter;
		
				//Kick control		
				Rectangle character_kick_rectangle;	
				Body player_body;
				String player_movement;
		
		//Enemy Getting Damage
		public Boolean is_enemy_getting_damage;
		String e_damage_direction;
		Boolean Is_Kicking_Animation_Finished;
		
		//losing enemy
		float final_enemy_position_x;
		float final_enemy_position_y;		
		
		  SpriteBatch boss_talk_baloon_sprite;
		  public Texture boss_talk_baloon_texture;
		  
		  Boolean is_jumping_on_platform, barreer_contact;	 
		  
		  public String shuriken_movement;
		  public Boolean can_throw_shuriken;
		  
		  int how_many_kick;
		  Boolean alt_kick_time;
		  String feint_direction;		
		  Boolean is_feinting_down;	  
		
	       public Boolean is_boss_talking;	
	       public Enemy_UI enemy_ui;	       
	          
	       //Enemy drawing properties...
	        float enemy_draw_width;
	        float enemy_draw_height; 
	        Sprite sp_enemy;	    
		
	public Enemy(Vector2 Enemy_position, Vector2 Player_Position, OrthographicCamera Camera,String Enemy_Type, int Enemy_Level)
	{		
        this.enemy_position = Enemy_position;      
        this.enemy_batch = new SpriteBatch();
        this.player_position = Player_Position;
        this.border_character = new Rectangle();
        this.border_enemy = new Rectangle();
        //this.test_shape = new ShapeRenderer();
        this.can_kick = true;
        this.e_camera = Camera;
        this.enemy_type = Enemy_Type;
        this.is_enemy_a_boss = false;
        this.enemy_level = Enemy_Level;
        this.can_enemy_jump = true;        
        this.life = 250;
        this.is_enemy_dead = false;      
        this.distance_enemy_player = 0;              
        this.feet_enemy = new Rectangle();       
        this.e_movement = "standing_right";  
        this.enable_animation = true;
        this.lost_sound_counter = 0;
        this.sound_fx_enemy_lost = Gdx.audio.newSound(Gdx.files.internal("Audio/enemy_lost.wav"));
        this.is_boss_talking = true;      
        
        this.e_kick_timer = 0;    
        this.is_enemy_getting_damage =false;
        this.e_damage_direction = "";
        this.Is_Kicking_Animation_Finished = true;
      //looks like the best values are 69 for normal level and 64 for hard level...
        if(Game_Elements.difficulty.equals("normal"))
        	this.e_kicking_time_limit = 89;
        if(Game_Elements.difficulty.equals("hard"))
            this.e_kicking_time_limit = 78;  
        
        this.final_enemy_position_y = 0;
        this.final_enemy_position_x = 0;
        
        this.boss_talk_baloon_sprite = new SpriteBatch();
        this.boss_talk_baloon_texture = new Texture(Gdx.files.internal("Game_Art/Talk_Baloons/boss_baloons/boss_talk_baloon_0" + Game_Elements.GetLevelData()+ ".png"));
        
        this.is_jumping_on_platform = false;    
        this.shuriken_movement ="";
        this.can_throw_shuriken = true;
        
        this.how_many_kick = 0;
        this.alt_kick_time = false;
        this.feint_direction ="feint_right";     
        this.is_feinting_down = false;       
        
        this.enemy_ui = new Enemy_UI(is_enemy_a_boss);  
        this.sp_enemy = new Sprite();     
        barreer_contact = false;
    }
	
	public void Animate_Enemy_Starter(int the_enemy_index)
	{	
		enemy_draw_width  = 21.5f/10;
	    enemy_draw_height = 22.5f/10; 
	    sp_enemy.setSize(enemy_draw_width, enemy_draw_height);
    	int row=8;
    	int col=4;
    	//set the enemy texture  
    	//System.out.println("enemy index : " + the_enemy_index + " enemy level: " + enemy_level);
    	if(the_enemy_index==7)
    		is_enemy_a_boss = true;
    	if(the_enemy_index<7)
    		is_enemy_a_boss = false;
    	if(!is_enemy_a_boss)
    	enemy_animation_spread_sheet = new Texture(Gdx.files.internal("Game_Art/Enemy Sheets/enemyType_0" +(the_enemy_index+1)+ ".png"));   	
    	if(is_enemy_a_boss)
    	  	enemy_animation_spread_sheet = new Texture(Gdx.files.internal("Game_Art/Enemy Sheets/boss_0" + enemy_level + ".png"));
    	
    	TextureRegion[][] tmp = TextureRegion.split(enemy_animation_spread_sheet, 
    			enemy_animation_spread_sheet.getWidth()/col, enemy_animation_spread_sheet.getHeight()/row);
		frames_enemy= new TextureRegion[col*row];
				
		int index = 0;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				frames_enemy[index++] = tmp[i][j];
			}
		}
	}  
	
	public void Animate_Enemy_Lost(int the_enemy_index_for_lost)
	{
	
    	int row=2;
    	int col=4;    	

			enemy_draw_width  = 27.5f/10;
			enemy_draw_height = 28.5f/10; 
			
			if(the_enemy_index_for_lost==7)
	    		is_enemy_a_boss = true;
	    	if(the_enemy_index_for_lost<7)
	    		is_enemy_a_boss = false;
			
			if(!is_enemy_a_boss)
			enemy_animation_spread_sheet_lost = 
			new Texture(Gdx.files.internal("Game_Art/Enemy Sheets/enemyLostType_0" + (the_enemy_index_for_lost+1) + ".png"));        	
    		
			else
    		enemy_animation_spread_sheet_lost = 
    		new Texture(Gdx.files.internal("Game_Art/Enemy Sheets/boss_lost0" + enemy_level + ".png"));		
    	TextureRegion[][] tmp = TextureRegion.split(enemy_animation_spread_sheet_lost, enemy_animation_spread_sheet_lost.getWidth()/col, enemy_animation_spread_sheet_lost.getHeight()/row);
		frames_enemy_lost = new TextureRegion[col*row];
				
		int index = 0;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				frames_enemy_lost[index++] = tmp[i][j];
			}
		}	
	}	
	
	public void Animate_Lost_Right()
	{	
    	
		frames_enemy_lost_right = new TextureRegion[4];
		
		frames_enemy_lost_right[0] = frames_enemy_lost[4];
		frames_enemy_lost_right[1] = frames_enemy_lost[5];
		frames_enemy_lost_right[2] = frames_enemy_lost[6];
		frames_enemy_lost_right[3] = frames_enemy_lost[7];
				
		
		enemy_animation_lost_right = new Animation<TextureRegion>(0.132f, frames_enemy_lost_right);
		enemy_animation_lost_right.setPlayMode(PlayMode.NORMAL);
		stateTime_lost_right = 0f;
		currentFrame_lost_right = frames_enemy_lost_right[0];
	}
	public void Animate_Lost_Left()
	{	
    	
		frames_enemy_lost_left = new TextureRegion[4];
		
		frames_enemy_lost_left[0] = frames_enemy_lost[0];
		frames_enemy_lost_left[1] = frames_enemy_lost[1];
		frames_enemy_lost_left[2] = frames_enemy_lost[2];
		frames_enemy_lost_left[3] = frames_enemy_lost[3];
				
		
		enemy_animation_lost_left = new Animation<TextureRegion>(0.132f, frames_enemy_lost_left);
		enemy_animation_lost_left.setPlayMode(PlayMode.NORMAL);
		stateTime_lost_left = 0f;
		currentFrame_lost_left = frames_enemy_lost_left[0];
	}
	
	public void Animate_Enemy_Right_Kick()
	{	
    	
		frames_enemy_right_kick = new TextureRegion[4];
		
		frames_enemy_right_kick[0] = frames_enemy[12];
		frames_enemy_right_kick[1] = frames_enemy[13];
		frames_enemy_right_kick[2] = frames_enemy[14];
		frames_enemy_right_kick[3] = frames_enemy[15];		
	
		enemy_animation_right_kick = new Animation<TextureRegion>(0.0825f, frames_enemy_right_kick);				
		enemy_animation_right_kick.setPlayMode(PlayMode.NORMAL);
		stateTime_right_kick = 0f;
		currentFrame_right_kick = frames_enemy_right_kick[0];
	}
	public void alt_Animate_Enemy_Right_Kick()
	{	
    	
		alt_frames_enemy_right_kick = new TextureRegion[4];
		
		alt_frames_enemy_right_kick[0] = frames_enemy[24];
		alt_frames_enemy_right_kick[1] = frames_enemy[25];
		alt_frames_enemy_right_kick[2] = frames_enemy[26];
		alt_frames_enemy_right_kick[3] = frames_enemy[27];
				
		
		alt_enemy_animation_right_kick = new Animation<TextureRegion>(0.09f, alt_frames_enemy_right_kick);
		alt_enemy_animation_right_kick.setPlayMode(PlayMode.NORMAL);
		alt_stateTime_right_kick = 0f;
		alt_currentFrame_right_kick = alt_frames_enemy_right_kick[0];
	}
	
		public void Animate_Enemy_Left_Kick()
	{	
		frames_enemy_left_kick = new TextureRegion[4];
		
		frames_enemy_left_kick[0] = frames_enemy[0];
		frames_enemy_left_kick[1] = frames_enemy[1];
		frames_enemy_left_kick[2] = frames_enemy[2];
		frames_enemy_left_kick[3] = frames_enemy[3]; 
		
		enemy_animation_left_kick = new Animation<TextureRegion>(0.0825f, frames_enemy_left_kick);	
		enemy_animation_left_kick.setPlayMode(PlayMode.NORMAL);
		stateTime_left_kick = 0f;
		currentFrame_left_kick = frames_enemy_left_kick[0];
	}
		public void alt_Animate_Enemy_Left_Kick()
	{	
		alt_frames_enemy_left_kick = new TextureRegion[4];
		
		alt_frames_enemy_left_kick[0] = frames_enemy[28];
		alt_frames_enemy_left_kick[1] = frames_enemy[29];
		alt_frames_enemy_left_kick[2] = frames_enemy[30];
		alt_frames_enemy_left_kick[3] = frames_enemy[31]; 				
		
		alt_enemy_animation_left_kick = new Animation<TextureRegion>(0.09f, alt_frames_enemy_left_kick);
		alt_enemy_animation_left_kick.setPlayMode(PlayMode.NORMAL);
		alt_stateTime_left_kick = 0f;
		alt_currentFrame_left_kick = alt_frames_enemy_left_kick[0];
	}
	public void Animate_Enemy_Walking_Right()
	{	
    	
		frames_enemy_walking_right = new TextureRegion[4];
		
		frames_enemy_walking_right[0] = frames_enemy[8];
		frames_enemy_walking_right[1] = frames_enemy[9];
		frames_enemy_walking_right[2] = frames_enemy[10];
		frames_enemy_walking_right[3] = frames_enemy[11];	 
		  
		enemy_animation_walking_right = new Animation<TextureRegion>(0.116f, frames_enemy_walking_right);
		enemy_animation_walking_right.setPlayMode(PlayMode.LOOP);
		stateTime_walking_right = 0f;
		currentFrame_walking_right = frames_enemy_walking_right[0];
	}
	public void Animate_Standing_Right()
	{
		frames_standing_right = new TextureRegion[1];
		frames_standing_right[0] = frames_enemy_walking_right[2];
		
		enemy_animation_standing_right = new Animation<TextureRegion>(0.1f, frames_standing_right);
		enemy_animation_standing_right.setPlayMode(PlayMode.LOOP);
		stateTime_standing_right = 0f;
		currentFrame_standing_right = frames_standing_right[0];
	}
	
	public void Animate_Enemy_Walking_Left()
	{	
    	
		frames_enemy_walking_left = new TextureRegion[4];
		
		frames_enemy_walking_left[0] = frames_enemy[4];
		frames_enemy_walking_left[1] = frames_enemy[5];
		frames_enemy_walking_left[2] = frames_enemy[6];
		frames_enemy_walking_left[3] = frames_enemy[7];				
		
		
		enemy_animation_walking_left = new Animation<TextureRegion>(0.116f, frames_enemy_walking_left);
		enemy_animation_walking_left.setPlayMode(PlayMode.LOOP);
		stateTime_walking_left = 0f;
		currentFrame_walking_left = frames_enemy_walking_left[0];
	}
	public void Animate_Standing_Left()
	{
		frames_standing_left = new TextureRegion[1];
		frames_standing_left[0] = frames_enemy_walking_left[2];
		
		enemy_animation_standing_left = new Animation<TextureRegion>(0.1f, frames_standing_left);
		enemy_animation_standing_left.setPlayMode(PlayMode.LOOP);
		stateTime_standing_left = 0f;
		currentFrame_standing_left = frames_standing_left[0];
	}
	public void Animate_Enemy_Getting_Impact_From_Right()
	{	

		frames_enemy_getting_impact_from_right = new TextureRegion[4];
		
		frames_enemy_getting_impact_from_right[0] = frames_enemy[16];
		frames_enemy_getting_impact_from_right[1] = frames_enemy[17];
		frames_enemy_getting_impact_from_right[2] = frames_enemy[18];
		frames_enemy_getting_impact_from_right[3] = frames_enemy[19];
				
		
		enemy_animation_getting_impact_from_right = new Animation<TextureRegion>(0.108f, frames_enemy_getting_impact_from_right);
		enemy_animation_getting_impact_from_right.setPlayMode(PlayMode.NORMAL);
		stateTime_getting_impact_from_right = 0f;
		currentFrame_getting_impact_from_right = frames_enemy_getting_impact_from_right[0];
	}
	
	public void Animate_Enemy_Getting_Impact_From_Left()
	{

		frames_enemy_getting_impact_from_left = new TextureRegion[4];
		
		frames_enemy_getting_impact_from_left[0] = frames_enemy[19];
		frames_enemy_getting_impact_from_left[1] = frames_enemy[20];
		frames_enemy_getting_impact_from_left[2] = frames_enemy[21];
		frames_enemy_getting_impact_from_left[3] = frames_enemy[22];
				
		
		enemy_animation_getting_impact_from_left = new Animation<TextureRegion>(0.108f, frames_enemy_getting_impact_from_left);
		enemy_animation_getting_impact_from_left.setPlayMode(PlayMode.NORMAL);
		stateTime_getting_impact_from_left = 0f;
		currentFrame_getting_impact_from_left = frames_enemy_getting_impact_from_left[0];
	}	
	
	public void Animate_Enemy_Throw_Shuriken_Right()
	{	
    	
		frames_enemy_throw_shuriken_right = new TextureRegion[4];
		
		frames_enemy_throw_shuriken_right[0] = frames_enemy[12];
		frames_enemy_throw_shuriken_right[1] = frames_enemy[13];
		frames_enemy_throw_shuriken_right[2] = frames_enemy[14];
		frames_enemy_throw_shuriken_right[3] = frames_enemy[15];				
		
		enemy_animation_throw_shuriken_right = new Animation<TextureRegion>(0.075f, frames_enemy_throw_shuriken_right);
		enemy_animation_throw_shuriken_right.setPlayMode(PlayMode.NORMAL);
		stateTime_throw_shuriken_right = 0;
		currentFrame_throw_shuriken_right = frames_enemy_throw_shuriken_right[0];
	}
	public void Animate_Enemy_Throw_Shuriken_Left()
	{	
    	
		frames_enemy_throw_shuriken_left = new TextureRegion[4];
		
		frames_enemy_throw_shuriken_left[0] = frames_enemy[0];
		frames_enemy_throw_shuriken_left[1] = frames_enemy[1];
		frames_enemy_throw_shuriken_left[2] = frames_enemy[2];
		frames_enemy_throw_shuriken_left[3] = frames_enemy[3];				
		
		enemy_animation_throw_shuriken_left = new Animation<TextureRegion>(0.075f, frames_enemy_throw_shuriken_left);
		enemy_animation_throw_shuriken_left.setPlayMode(PlayMode.NORMAL);
		stateTime_throw_shuriken_left = 0;
		currentFrame_throw_shuriken_left = frames_enemy_throw_shuriken_left[0];
	}
	
	public void Draw_Enemy(OrthographicCamera camera, float Delta_Time)
	{	
		// All moves bundled in this function...
        
		//Walk To the right		
		if(e_movement.equals("walking_right") && !is_enemy_getting_damage && !shuriken_movement.contains("shuriken"))
				Draw_Enemy_Walking_Right(camera);		
		//Walk To the left	
		if(e_movement.equals("walking_left") && !is_enemy_getting_damage && !shuriken_movement.contains("shuriken"))
				Draw_Enemy_Walking_Left(camera);
		//Standing Left or Right
		if(e_movement.equals("standing_right") && !is_enemy_getting_damage && !shuriken_movement.contains("shuriken"))
			Draw_Enemy_Standing_Right(e_camera);
		if(e_movement.equals("standing_left") && !is_enemy_getting_damage && !shuriken_movement.contains("shuriken"))
			Draw_Enemy_Standing_Left(e_camera);
		
		
		//Attack Moves...
		//Attack Right	
		if(e_movement.equals("kicking_right"))
			Draw_Enemy_Right_Kick(camera);	  
		//Attack Left
		if(e_movement.equals("kicking_left") )
			Draw_Enemy_Left_Kick(camera);
		//Enemy throwing shurikens to the right
	if(shuriken_movement.equals("shuriken_right"))
			Draw_Enemy_Throw_Shuriken_Right(camera);
	//Enemy throwing shurikens to the left
	if(shuriken_movement.equals("shuriken_left"))
			Draw_Enemy_Throw_Shuriken_Left(camera);		
		// Enemy is Getting Damage
		if(!e_movement.contains("kicking") && is_enemy_getting_damage)
		{		
				if(e_damage_direction.equals("getting_impact_from_right"))
				{					
					Draw_Enemy_Getting_Impact_From_Right(camera);
				}
				if(e_damage_direction.equals("getting_impact_from_left"))
				{
					Draw_Enemy_Getting_Impact_From_Left(camera);
				}
		}
	}
	
	public void Draw_Enemy_Walking_Right(OrthographicCamera camera)
	{	
		if(Main_Unit.is_player_alive)
		{
			stateTime_walking_right +=Gdx.graphics.getDeltaTime();		
			if(enemy_animation_walking_right.isAnimationFinished(stateTime_walking_right))
			{
				stateTime_walking_right = 0f;				
			}
		}
		
		if(!Main_Unit.is_player_alive || Main_Unit.is_intro_mode)
			currentFrame_walking_right = enemy_animation_walking_right.getKeyFrames()[0];			
				
				enemy_batch.enableBlending();
				enemy_batch.begin();
				enemy_batch.setProjectionMatrix(camera.combined);
				sp_enemy.setRegion(currentFrame_walking_right);				
				sp_enemy.draw(enemy_batch);
				enemy_batch.end();
	}
	public void Draw_Enemy_Standing_Right(OrthographicCamera _camera)
	{
		stateTime_standing_right += Gdx.graphics.getDeltaTime();
		if(enemy_animation_standing_right.isAnimationFinished(stateTime_standing_right))
			stateTime_standing_right = 0;
		enemy_batch.enableBlending();
		enemy_batch.begin();
		enemy_batch.setProjectionMatrix(_camera.combined);
		sp_enemy.setRegion(currentFrame_standing_right);
		sp_enemy.draw(enemy_batch);		
		enemy_batch.end();
	}
	
	public void Draw_Enemy_Walking_Left(OrthographicCamera camera)
	{	
		if(Main_Unit.is_player_alive)
		{
			stateTime_walking_left +=Gdx.graphics.getDeltaTime();				
			if(enemy_animation_walking_left.isAnimationFinished(stateTime_walking_left))
			{
				stateTime_walking_left = 0f;			
			}
		}
		if(!Main_Unit.is_player_alive || Main_Unit.is_intro_mode)
			currentFrame_walking_left = enemy_animation_walking_left.getKeyFrames()[0];		
     
		enemy_batch.enableBlending();
		enemy_batch.begin();
		enemy_batch.setProjectionMatrix(camera.combined);
		sp_enemy.setRegion(currentFrame_walking_left);	
		sp_enemy.draw(enemy_batch);
		enemy_batch.end();
	}
	public void Draw_Enemy_Standing_Left(OrthographicCamera _camera)
	{
		stateTime_standing_left += Gdx.graphics.getDeltaTime();
		if(enemy_animation_standing_left.isAnimationFinished(stateTime_standing_left))
			stateTime_standing_left = 0;
		enemy_batch.enableBlending();
		enemy_batch.begin();
		enemy_batch.setProjectionMatrix(_camera.combined);
		sp_enemy.setRegion(currentFrame_standing_left);
		sp_enemy.draw(enemy_batch);		
		enemy_batch.end();
	}
	
	public void Draw_Enemy_Right_Kick(OrthographicCamera camera)
	{		
		shuriken_movement = "";
		if(is_enemy_a_boss && enemy_level == 3) 
			enemy_animation_right_kick.setFrameDuration(0.115f);			    	
    	if(is_enemy_a_boss && enemy_level == 4)	
			enemy_animation_right_kick.setFrameDuration(0.105f);		
		if(is_enemy_a_boss && enemy_level == 5)	
			enemy_animation_right_kick.setFrameDuration(0.115f);			
	
		if(alt_kick_time)
		{	
			   Is_Kicking_Animation_Finished = false;			   
			   is_enemy_getting_damage = false;	
			   alt_stateTime_right_kick +=Gdx.graphics.getDeltaTime();			
			   if(alt_enemy_animation_right_kick.isAnimationFinished(alt_stateTime_right_kick))
			   {			
				   how_many_kick +=1;
				   if(Main_Unit.is_player_alive)
				   {				
					   alt_stateTime_right_kick = 0;					  			
					   enable_animation = true;		
					   Is_Kicking_Animation_Finished = true;					   
				   }
				   if(!Main_Unit.is_player_alive)
				   {
					   how_many_kick = 0;					   				   
				   }
			   }		
			   //alternative kick right draw				
				enemy_batch.enableBlending();
			    enemy_batch.begin();
			    enemy_batch.setProjectionMatrix(camera.combined);
			 //IMPORTANt :this part controls the animation of the enemies which are meant to be boss : matrix system for animation sheet to be divided properly.
			    //Kicking algorithm For the enemies which are meant to be boss.
			    if(is_enemy_a_boss )
			    {
			    	if(enemy_level == 1)
			    	{
			    		alt_enemy_animation_right_kick.setFrameDuration(0.108f);
			    		enemy_animation_right_kick.setFrameDuration(0.09f);
			    		sp_enemy.setRegion(alt_currentFrame_right_kick);
			    		sp_enemy.draw(enemy_batch);			    			
			    	}
			    	if(enemy_level == 2)
			    	{
			    		alt_enemy_animation_right_kick.setFrameDuration(0.1f);
			    		enemy_animation_right_kick.setFrameDuration(0.09f);
			    		sp_enemy.setRegion(currentFrame_right_kick);
			    		sp_enemy.draw(enemy_batch);			    		
			    	}			    
			    	// for the rest of the levels...
			    	for(int i=3; i<10;i++)
			    	{
			    		if(enemy_level ==i)
			    		{
			    		alt_enemy_animation_right_kick.setFrameDuration(0.1f);			    		
			    		enemy_animation_right_kick.setFrameDuration(0.09f);
			    		if(i==7)
			    			enemy_animation_right_kick.setFrameDuration(0.08f);
			    		if(i==4)
			    		{
			    			alt_enemy_animation_right_kick.setFrameDuration(0.082f);		
			    			enemy_animation_right_kick.setFrameDuration(0.082f);
			    		}
			    		if(i==8)
			    		{
			    			alt_enemy_animation_right_kick.setFrameDuration(0.09f);			    		  
			    		}
			    		if(i==9)
			    		{
			    			enemy_animation_right_kick.setFrameDuration(0.096f);
			    		}
			    			sp_enemy.setRegion(alt_currentFrame_right_kick);
			    			sp_enemy.draw(enemy_batch);			    			
			    		}
			    	}
			    }
			    //Kicking algorithm for the other enemies...
			    if(!is_enemy_a_boss)
			    {
			    	sp_enemy.setRegion(alt_currentFrame_right_kick);
			    	sp_enemy.draw(enemy_batch);			    	  
			    }			  
			    enemy_batch.end();
		}
		if(!alt_kick_time)
		{
			   Is_Kicking_Animation_Finished = false;			   
			   is_enemy_getting_damage = false;	
			   stateTime_right_kick +=Gdx.graphics.getDeltaTime();			
			   if(enemy_animation_right_kick.isAnimationFinished(stateTime_right_kick))
			   {	
				   how_many_kick+=1;				  
				   if(Main_Unit.is_player_alive)
				   {				
					   stateTime_right_kick = 0;					  			
					   enable_animation = true;		
					   Is_Kicking_Animation_Finished = true;					   
				   }
				   if(!Main_Unit.is_player_alive)
				   {					
					   how_many_kick = 0;
				   		currentFrame_right_kick = enemy_animation_right_kick.getKeyFrames()[0];				   	
				   }
			   }		   		
			   		enemy_batch.enableBlending();
			   		enemy_batch.begin();
			   		enemy_batch.setProjectionMatrix(camera.combined);
			   		sp_enemy.setRegion(currentFrame_right_kick);
			   		sp_enemy.draw(enemy_batch);			   			
			   		enemy_batch.end();	
			}		
	}
	
	public void Draw_Enemy_Left_Kick(OrthographicCamera camera)
	{	
		shuriken_movement = "";
		if(is_enemy_a_boss && enemy_level == 3)
    	   enemy_animation_left_kick.setFrameDuration(0.115f);
    	if(is_enemy_a_boss && enemy_level == 4)
			enemy_animation_left_kick.setFrameDuration(0.105f);	
		if(is_enemy_a_boss && enemy_level ==5)
			enemy_animation_left_kick.setFrameDuration(0.115f);
		if(alt_kick_time)
		{	   
			  
			   Is_Kicking_Animation_Finished = false;			   
			   is_enemy_getting_damage = false;	
			   alt_stateTime_left_kick +=Gdx.graphics.getDeltaTime();			
			   if(alt_enemy_animation_left_kick.isAnimationFinished(alt_stateTime_left_kick))
			   {			
				   how_many_kick +=1;
				   if(Main_Unit.is_player_alive)
				   {				
					   alt_stateTime_left_kick = 0;					  			
					   enable_animation = true;		
					   Is_Kicking_Animation_Finished = true;					   
				   }
				   if(!Main_Unit.is_player_alive)
				   {
					   how_many_kick = 0;					   
				   }
			   }		   
			   //alternative kick left draw			
				enemy_batch.enableBlending();
			    enemy_batch.begin();
			    enemy_batch.setProjectionMatrix(camera.combined);
			    //IMPORTANt :this part controls the animation of the enemies which are meant to be boss : matrix system for animation sheet to be divided properly.
			    //Kicking algorithm For the enemies which are meant to be boss.
			    if(is_enemy_a_boss)
			    {
			    	if(enemy_level == 1)
			    	{
			    		alt_enemy_animation_left_kick.setFrameDuration(0.108f);
			    		enemy_animation_left_kick.setFrameDuration(0.09f);
			    		sp_enemy.setRegion(alt_currentFrame_left_kick);
			    		sp_enemy.draw(enemy_batch);			    		
			    	}
			    	if(enemy_level == 2)
			    	{
			    		alt_enemy_animation_left_kick.setFrameDuration(0.1f);
			    		enemy_animation_left_kick.setFrameDuration(0.09f);
			    		sp_enemy.setRegion(alt_currentFrame_left_kick);
			    		sp_enemy.draw(enemy_batch);			    			
			    	} 			    	
			    	//for the rest of the levels...
			    	for(int i=3; i<10;i++)
			    	{
			    		if(enemy_level ==i)
			    		{
			    			alt_enemy_animation_left_kick.setFrameDuration(0.1f);
			    			enemy_animation_left_kick.setFrameDuration(0.09f);
			    			if(i==7)
				    			enemy_animation_left_kick.setFrameDuration(0.08f);
			    			if(i==4)
			    			{
			    				alt_enemy_animation_left_kick.setFrameDuration(0.082f);
			    				enemy_animation_left_kick.setFrameDuration(0.082f);
			    			}
			    			if(i==8)
				    		{
				    			alt_enemy_animation_left_kick.setFrameDuration(0.09f);			    		  
				    		}
				    		if(i==9)
				    		{
				    			enemy_animation_left_kick.setFrameDuration(0.096f);
				    		}
				    		sp_enemy.setRegion(alt_currentFrame_left_kick);
				    		sp_enemy.draw(enemy_batch);			    				
			    		}
			    	}
			    		
			    }
			    if(!is_enemy_a_boss)
			    {
			    	sp_enemy.setRegion(alt_currentFrame_left_kick);
			    	sp_enemy.draw(enemy_batch);
			    }
			   
			    enemy_batch.end();	

		}
		if(!alt_kick_time)
		{
			   Is_Kicking_Animation_Finished = false;			   
			   is_enemy_getting_damage = false;	
			   stateTime_left_kick +=Gdx.graphics.getDeltaTime();			
			   if(enemy_animation_left_kick.isAnimationFinished(stateTime_left_kick))
			   {	
				   how_many_kick+=1;				
				   if(Main_Unit.is_player_alive)
				   {				
					   stateTime_left_kick = 0;					  			
					   enable_animation = true;		
					   Is_Kicking_Animation_Finished = true;					   
				   }
				   if(!Main_Unit.is_player_alive)
				   {
					   how_many_kick = 0;
				   	   currentFrame_left_kick = enemy_animation_left_kick.getKeyFrames()[0];				   	
				   }
			   }			 
				enemy_batch.enableBlending();
			    enemy_batch.begin();
			    enemy_batch.setProjectionMatrix(camera.combined);
			    sp_enemy.setRegion(currentFrame_left_kick);
			    sp_enemy.draw(enemy_batch);			  	
			    enemy_batch.end();	
			}			  
	}
	
	public void Draw_Enemy_Getting_Impact_From_Right(OrthographicCamera camera)
	{		
		if(!is_enemy_a_boss)
		life -= 1.75;		
		if(is_enemy_a_boss)
			life -= 0.395;		
		
		currentFrame_getting_impact_from_right = enemy_animation_getting_impact_from_right.getKeyFrame(stateTime_getting_impact_from_right+0.12f,false);
		enemy_batch.enableBlending();
		enemy_batch.begin();
		enemy_batch.setProjectionMatrix(camera.combined);	
		sp_enemy.setRegion(currentFrame_getting_impact_from_right);
		sp_enemy.draw(enemy_batch);	   	
		enemy_batch.end();
		stateTime_getting_impact_from_right +=Gdx.graphics.getDeltaTime();		
			
		if(enemy_animation_getting_impact_from_right.isAnimationFinished(stateTime_getting_impact_from_right))
		{			
				stateTime_getting_impact_from_right = 0f;
				is_enemy_getting_damage = false;				
		}	
	}
	
	public void Draw_Enemy_Getting_Impact_From_Left(OrthographicCamera camera)
	{	
		if(!is_enemy_a_boss)
			life -= 1.75;		
			if(is_enemy_a_boss)
				life -=  0.395;			
				currentFrame_getting_impact_from_left = enemy_animation_getting_impact_from_left.getKeyFrame(stateTime_getting_impact_from_left+0.12f,false);
				enemy_batch.enableBlending();
				enemy_batch.begin();
				enemy_batch.setProjectionMatrix(camera.combined);		
				sp_enemy.setRegion(currentFrame_getting_impact_from_left);
				sp_enemy.draw(enemy_batch);	
				enemy_batch.end();
				stateTime_getting_impact_from_left +=Gdx.graphics.getDeltaTime();	
					
				if(enemy_animation_getting_impact_from_left.isAnimationFinished(stateTime_getting_impact_from_left))
				{
				stateTime_getting_impact_from_left = 0f;
				is_enemy_getting_damage = false;		
				}	
	}
	public void Draw_Enemy_Throw_Shuriken_Right(OrthographicCamera camera)
	{	
		   stateTime_throw_shuriken_right +=Gdx.graphics.getDeltaTime();
		   currentFrame_throw_shuriken_right = enemy_animation_throw_shuriken_right.getKeyFrame(stateTime_throw_shuriken_right);
		  
		   if(enemy_animation_throw_shuriken_right.isAnimationFinished(stateTime_throw_shuriken_right))
		   {			  
			   can_throw_shuriken = false;		
			   shuriken_movement = "";			 
			   if(Main_Unit.is_player_alive)
			   {				
				   stateTime_throw_shuriken_right = 0;				 			   
			   }
			   if(!Main_Unit.is_player_alive)
			   {			
			   		currentFrame_throw_shuriken_right = enemy_animation_throw_shuriken_right.getKeyFrames()[0];
			   		can_throw_shuriken = false;
			   }
		   }		   		
		   		can_throw_shuriken = true;	   	
		   		enemy_batch.enableBlending();
		   		enemy_batch.begin();
		   		enemy_batch.setProjectionMatrix(camera.combined);
		   		sp_enemy.setRegion(currentFrame_throw_shuriken_right);
		   		sp_enemy.draw(enemy_batch);	
		   		enemy_batch.end();		
		   		
	}
	public void Draw_Enemy_Throw_Shuriken_Left(OrthographicCamera camera)
	{	
		   stateTime_throw_shuriken_left +=Gdx.graphics.getDeltaTime();			
		   currentFrame_throw_shuriken_left = enemy_animation_throw_shuriken_left.getKeyFrame(stateTime_throw_shuriken_left);
		   if(enemy_animation_throw_shuriken_left.isAnimationFinished(stateTime_throw_shuriken_left))
		   {		
			   can_throw_shuriken = false;
			   shuriken_movement ="";
			   if(Main_Unit.is_player_alive)
			   {				
				  stateTime_throw_shuriken_left = 0;		   				   
			   }
			   if(!Main_Unit.is_player_alive)
			   {			
			   		currentFrame_throw_shuriken_left = enemy_animation_throw_shuriken_left.getKeyFrames()[0];
			   		can_throw_shuriken = false;
			   }
		   }	   
		   		can_throw_shuriken = true;		   		
		   		enemy_batch.enableBlending();
		   		enemy_batch.begin();
		   		enemy_batch.setProjectionMatrix(camera.combined);
		   		sp_enemy.setRegion(currentFrame_throw_shuriken_left);
		   		sp_enemy.draw(enemy_batch);	
		   		enemy_batch.end();	
	}
	
	public void Draw_boss_talk_baloon()
	{
		boss_talk_baloon_sprite.enableBlending();
		boss_talk_baloon_sprite.begin();
		boss_talk_baloon_sprite.setProjectionMatrix(e_camera.combined);
		boss_talk_baloon_sprite.draw(boss_talk_baloon_texture, enemy_position.x+border_enemy.getWidth()/1.825f, enemy_position.y+border_enemy.getHeight()/1.825f,180/100f,128/100f);	
		boss_talk_baloon_sprite.end();		
	}
	private Boolean check_Damage_Directions()
	{
		Boolean check_statement = true;
		//statement 01
		if( player_position.x<enemy_position.x && e_movement.contains("left") && player_movement.contains("left") )
		{
			check_statement = false;
		}
		//statement 02
		if( player_position.x>enemy_position.x && e_movement.contains("right") && player_movement.contains("right") )
		{
			check_statement = false;
		}		
		return check_statement;
	}
	
	public void Draw_Enemy_lost(OrthographicCamera camera)
	{	
		enemy_body.setLinearVelocity(new Vector2(0,enemy_body.getLinearVelocity().y));
		
			final_enemy_position_x = enemy_body.getPosition().x - border_enemy.getWidth()*1.125f;	
		enemy_position.y = enemy_position.y - border_enemy.getHeight()/1.125f;
		float feint_power = 20f;
	
		//Feint right...
				if(feint_direction.equals("feint_right"))
				{
					if(is_feinting_down)
					enemy_body.applyLinearImpulse(new Vector2(feint_power,0f), enemy_body.getWorldCenter(), true);
					is_feinting_down = true;
					if(lost_sound_counter == 1)
						sound_fx_enemy_lost.play(1f);			
						if(enemy_animation_lost_right.isAnimationFinished(stateTime_lost_right))
						{						
							is_feinting_down = false;
							lost_sound_counter = 100;		
								if(lost_sound_counter > 500)
						    		lost_sound_counter =100;		
								//enemy_body.setTransform(new Vector2(enemy_body.getPosition().x,enemy_body.getPosition().y),1.5708f);		
						}					
						stateTime_lost_right += Gdx.graphics.getDeltaTime();
						currentFrame_lost_right = enemy_animation_lost_right.getKeyFrame(stateTime_lost_right,false);
						enemy_batch.enableBlending();
						enemy_batch.begin();
						enemy_batch.setProjectionMatrix(camera.combined);	
						sp_enemy.setRegion(currentFrame_lost_right);
						sp_enemy.draw(enemy_batch);							
						enemy_batch.end();			
						lost_sound_counter +=1;	
					}
		//Feint Left
		if(feint_direction.equals("feint_left"))
		{		
			if(is_feinting_down)
				enemy_body.applyLinearImpulse(new Vector2(-feint_power,0f), enemy_body.getWorldCenter(), true);
			is_feinting_down = true;
			if(lost_sound_counter == 1)
				sound_fx_enemy_lost.play(1f);			
				if(enemy_animation_lost_left.isAnimationFinished(stateTime_lost_left))
				{			
					is_feinting_down = false;
					lost_sound_counter = 100;		
						if(lost_sound_counter > 500)
				    		lost_sound_counter =100;		
						//enemy_body.setTransform(new Vector2(enemy_body.getPosition().x,enemy_body.getPosition().y),1.5708f);		
				}	
			
				stateTime_lost_left += Gdx.graphics.getDeltaTime();
				currentFrame_lost_left = enemy_animation_lost_left.getKeyFrame(stateTime_lost_left,false);
				enemy_batch.enableBlending();
				enemy_batch.begin();
				enemy_batch.setProjectionMatrix(camera.combined);
				sp_enemy.setRegion(currentFrame_lost_left);
				sp_enemy.draw(enemy_batch);					
				enemy_batch.end();			
				lost_sound_counter +=1;	
			}	
	}	
	private void Draw_Boss_Energy_Bar()
	{	
		//enemy energy bar
		enemy_ui.Draw_Enemy_EnergyBar(life, is_enemy_dead, is_boss_talking);
	}
	
	public void Kick_Control(Rectangle Character_Kick_Rectangle, String Player_Movement)
	{		
	 	character_kick_rectangle = Character_Kick_Rectangle;
	 	player_movement = Player_Movement;
	 	
	 	if(character_kick_rectangle.overlaps(border_enemy) && Is_Kicking_Animation_Finished && check_Damage_Directions())
	 	{  		
	 	    is_enemy_getting_damage = true;	 	    
	 	    	
	 	    if(Player_Movement.contains("left"))
	 	    {	 	    	
	 	    	e_damage_direction = "getting_impact_from_right"; 	 	    			 	    		 	    			
	 	    }
	 	    
	 	    if(Player_Movement.contains("right"))
	 	    {
	 	    	e_damage_direction = "getting_impact_from_left";	    				   				 	    			    		 	    	
	 	    } 	    		 	    	
	 	}
	}	

	
	public void CheckGroundContact()
	{	
		for (Rectangle floor_rectangle : rectangles_of_gravity) 
		{
			if(floor_rectangle.overlaps(feet_enemy))
			{					
				can_enemy_jump =  true;					
			}
		}	 
	}
	
	public void SetEnemyPhysics(Body Enemy_Body, OrthographicCamera Camera, World Enemy_World)
	{		
		this.e_world = Enemy_World;	
		this.enemy_body = Enemy_Body;		
		b2d_debug_renderer = new Box2DDebugRenderer();	    
	    enemy_body.setFixedRotation(true);
	}
	
	public void SetGrounds(ArrayList<Rectangle> E_floor)
	{
		this.rectangles_of_gravity = E_floor;		
	}
	
	public void Jump()
	{			
		if(!is_enemy_a_boss)
		{
			currentFrame_left_kick = enemy_animation_left_kick.getKeyFrame(stateTime_left_kick,true);
			currentFrame_right_kick = enemy_animation_right_kick.getKeyFrame(stateTime_right_kick,true);	
		
			alt_currentFrame_left_kick = alt_enemy_animation_left_kick.getKeyFrame(alt_stateTime_left_kick,true);
			alt_currentFrame_right_kick = alt_enemy_animation_right_kick.getKeyFrame(alt_stateTime_right_kick,true);
		}
		//Platform Jumper...
		for(int r=0;r<rectangles_of_gravity.size();r++)
		{
			if(border_enemy.overlaps(rectangles_of_gravity.get(r)) && can_enemy_jump && !is_jumping_on_platform)
			{	
				is_jumping_on_platform = true;
				enemy_body.applyLinearImpulse(0f, enemy_jump_power, enemy_body.getWorldCenter().x, enemy_body.getWorldCenter().y, true);			
			}		
			if(!border_enemy.overlaps(rectangles_of_gravity.get(r)) && can_enemy_jump)
				is_jumping_on_platform = false;
		}			
		
		//Jump for the boss
		if(is_enemy_a_boss)
		{
			if(player_position.y > enemy_position.y + sp_enemy.getHeight()/2)
			{			
				if(can_enemy_jump)				
				{
					enemy_body.applyLinearImpulse(0f, enemy_jump_power, enemy_body.getWorldCenter().x, enemy_body.getWorldCenter().y, true);	
					can_enemy_jump = false;
					enemy_jump_power = 0f;					
				}			
			}
		}
		
	}
	
	public void Reset_Life()
	{
		life = 250;
	}
	private void FitEnemy2Body()
	{
		//Fix enemy position with b2dbody position...
		if(!is_enemy_a_boss)
		{
		enemy_position.x = enemy_body .getPosition().x-100/100f;		
  		enemy_position.y = enemy_body.getPosition().y-sp_enemy.getHeight()/2; 		
		}
		else 
		{
			enemy_position.x = enemy_body .getPosition().x-100/100f;		
	  		enemy_position.y = enemy_body.getPosition().y-sp_enemy.getHeight()/2;
		}
		sp_enemy.setPosition(enemy_position.x, enemy_position.y);
	}
	public void Enemy_AI(Vector2 Final_Player_Position, Body character_body, Rectangle _border_character, float Delta_Time)
	{		
		e_DeltaTime = Delta_Time;		
		if(how_many_kick ==0)
			alt_kick_time = false;
		if(how_many_kick ==1)
			alt_kick_time = true;
		if(how_many_kick == 2)
			alt_kick_time = false;
		if(how_many_kick ==3)
			alt_kick_time =  true;
		if(how_many_kick >= 4)
			how_many_kick = 0;	
		
		if(is_enemy_a_boss)
		{		
			Draw_Boss_Energy_Bar();
			currentFrame_left_kick = enemy_animation_left_kick.getKeyFrame(stateTime_left_kick,true);
			currentFrame_right_kick = enemy_animation_right_kick.getKeyFrame(stateTime_right_kick,true);	
			alt_currentFrame_left_kick = alt_enemy_animation_left_kick.getKeyFrame(alt_stateTime_left_kick,true);
			alt_currentFrame_right_kick = alt_enemy_animation_right_kick.getKeyFrame(alt_stateTime_right_kick,true);		
		}
		
		//Advanced Kick Algorithm	
		e_kick_timer += 1;		
		if(e_kick_timer > e_kicking_time_limit)
		{
			e_kick_timer = 0;			
		}		
		if(e_kick_timer == e_kicking_time_limit || enemy_body.getPosition().y>0)
			can_kick = true;
		if(e_kick_timer!= e_kicking_time_limit)
			can_kick= false;	
		
		//enemy_position = enemy_body.getPosition();		
		FitEnemy2Body();
		player_position = Final_Player_Position;	
		border_character.set(_border_character.x+9/100f, _border_character.y+48/100f, _border_character.width+20/100, _border_character.height);	    
		border_enemy.set(enemy_body.getPosition().x-48/100f, enemy_body.getPosition().y-50/100f, 100/100f, 130/100f);
		
		feet_enemy.set(border_enemy.x+38/100f, border_enemy.y-20/100f, 30/100f, 30/100f);
	    can_enemy_jump = false;	
		CheckGroundContact();		
		if(life>2f)
		{
			if(Math.abs(enemy_position.y-player_position.y)<2f && Math.abs(enemy_body.getLinearVelocity().y) < 3f)
			if(!Game_Elements.game_paused)
				Jump();
		}
			
		player_body = character_body;
		//currentFrame_lost_right = enemy_animation_lost_right.getKeyFrame(stateTime_lost_right + 0.12f,false);	
		//currentFrame_lost_left = enemy_animation_lost_left.getKeyFrame(stateTime_lost_left + 0.12f,false);	
	    //distance_enemy_player = Math.abs(enemy_position.x-player_position.x);
		distance_enemy_player = Math.sqrt( ( (player_position.x- enemy_position.x) * (player_position.x-enemy_position.x ) ) +  ( (player_position.y - enemy_position.y) * (player_position.y-enemy_position.y) ) );
	
	if(life>2f)
	{	
		is_enemy_dead = false;	
	  
	   if(enable_animation)
	   {
	   // Goes to the right	   
	   if(enemy_body.getPosition().x < player_body.getPosition().x && distance_enemy_player < 3.8f && !Game_Elements.game_paused)
	   {					
		   feint_direction = "feint_left";
						if(!can_kick)
						{   
							if(!barreer_contact)
							e_movement = "walking_right";
							else 
								e_movement = "standing_right";
							currentFrame_walking_right = enemy_animation_walking_right.getKeyFrame(stateTime_walking_right, false);						  
						}	
						if(distance_enemy_player < 2.6f)
						{						
							currentFrame_right_kick = enemy_animation_right_kick.getKeyFrame(stateTime_right_kick, false);
							alt_currentFrame_right_kick = alt_enemy_animation_right_kick.getKeyFrame(alt_stateTime_right_kick, false);
					    }			
						
						//BEAT IT TO THE RIGHT check closeness to the character to apply the force... Right Side...
						if(border_character.overlaps(border_enemy) && e_kick_timer == e_kicking_time_limit && distance_enemy_player<0.56f)
						{							
							can_kick = true;						    	
						}
						if(border_character.overlaps(border_enemy) && e_kick_timer != e_kicking_time_limit)
							can_kick = false;
							
							if(!border_character.overlaps(border_enemy))
								can_kick = false;
						
						//Hitting Right
						if(can_kick && !Main_Unit.is_intro_mode && !Main_Unit.is_flip_kicking)
						{						
							 e_movement = "kicking_right";							 
						     Main_Unit.is_player_getting_damage = true;
						     Main_Unit.damage_direction = "right_damage";
							 enable_animation = false;		
							 GameController.VibrateJoypad();
							 if(Game_Elements.difficulty.equals("normal") && !is_enemy_a_boss)						 
								 UI.player_energy -= 5f;							 
							 if(Game_Elements.difficulty.equals("hard") && !is_enemy_a_boss)							
								 UI.player_energy -= 6f;			
							 if(Game_Elements.difficulty.equals("normal") && is_enemy_a_boss)
								 UI.player_energy -= 6f;							 
							 if(Game_Elements.difficulty.equals("hard") && is_enemy_a_boss)							 
								 UI.player_energy -= 6.5f;
							 
								 e_kick_timer = 0;
						}									
							
						// simple walking to the right					
						if(!is_enemy_a_boss)
						{
							if(Main_Unit.is_player_alive && Math.abs(enemy_body.getLinearVelocity().x) < Math.abs(1.73f) )
							{							
								if(Math.abs(enemy_body.getLinearVelocity().x) <= 1.4f)
								{
									if(!Main_Unit.is_intro_mode)
										enemy_body.applyLinearImpulse(enemy_speed, 0f, enemy_position.x, enemy_position.y, true);
								}
							}
						}
						//boss walking right...
						if(is_enemy_a_boss)
						{
							if(Game_Elements.difficulty.equals("normal"))
							e_kicking_time_limit = 83;
							if(Game_Elements.difficulty.equals("hard"))
								e_kicking_time_limit = 72;						
							if(Main_Unit.is_player_alive && Math.abs(enemy_body.getLinearVelocity().x) < Math.abs(2.27f))
							{							
								if(Math.abs(enemy_body.getLinearVelocity().x) <= 1.6f)
								{
									if(!Main_Unit.is_intro_mode)
										enemy_body.applyLinearImpulse(enemy_speed*1.06f, 0f, enemy_position.x, enemy_position.y, true);
								}
							}
						}
		}
	   if(enemy_body.getPosition().x < player_body.getPosition().x && distance_enemy_player > 3.8f) 
		   e_movement = "standing_right";
					
    // Goes to the left					
	    if(enemy_body.getPosition().x > player_body.getPosition().x && distance_enemy_player < 3.8f && !Game_Elements.game_paused)
		{					
	    	feint_direction = "feint_right";
						if(!can_kick)
						{   
							if(!barreer_contact)
							e_movement = "walking_left";
							else 
								e_movement = "standing_left";
							currentFrame_walking_left = enemy_animation_walking_left.getKeyFrame(stateTime_walking_left, false);						
						}	
						if(distance_enemy_player < 2.6f)
						{						
							currentFrame_left_kick = enemy_animation_left_kick.getKeyFrame(stateTime_left_kick, false);		
							alt_currentFrame_left_kick = alt_enemy_animation_left_kick.getKeyFrame(alt_stateTime_left_kick, false);	
						}					
						
						//BEAT IT TO THE LEFT check closeness to the character for applying force...Left Side
						if(border_character.overlaps(border_enemy) && e_kick_timer == e_kicking_time_limit && distance_enemy_player<0.56f)
						{						
							can_kick = true;
						}
							if(border_character.overlaps(border_enemy) && e_kick_timer != e_kicking_time_limit)
							can_kick = false;
						
						if(!border_character.overlaps(border_enemy))
							can_kick = false;
						
						//Hitting Left
						if(can_kick && !Main_Unit.is_intro_mode && !Main_Unit.is_flip_kicking)
						{
							 e_movement = "kicking_left";						
							 Main_Unit.is_player_getting_damage = true;
							 Main_Unit.damage_direction = "left_damage";
							 enable_animation = false;	 
							 GameController.VibrateJoypad();
							 if(Game_Elements.difficulty.equals("normal") && !is_enemy_a_boss)											 
								 UI.player_energy -= 5f;						
							 if(Game_Elements.difficulty.equals("hard") && !is_enemy_a_boss)						
								 UI.player_energy -= 6f;						 
							 if(Game_Elements.difficulty.equals("normal") && is_enemy_a_boss)											 
								 UI.player_energy -= 6f;						
							 if(Game_Elements.difficulty.equals("hard") && is_enemy_a_boss)				
								 UI.player_energy -= 6.5f;
							 
								 e_kick_timer = 0;
						}				
						// simple walking to the left
						if(!is_enemy_a_boss)
						{							
							if(Main_Unit.is_player_alive && Math.abs(enemy_body.getLinearVelocity().x) < 1.73f)
							{
									if(Math.abs(enemy_body.getLinearVelocity().x) <= 1.4f)
									{
										if(!Main_Unit.is_intro_mode)
											enemy_body.applyLinearImpulse(-enemy_speed, 0f, enemy_position.x, enemy_position.y, true);
									}
							}
						}
						
						//boss walking left...
						if(is_enemy_a_boss)
						{
							if(Game_Elements.difficulty.equals("normal"))
								e_kicking_time_limit = 83;
								if(Game_Elements.difficulty.equals("hard"))
									e_kicking_time_limit = 72;							
							if(Main_Unit.is_player_alive && Math.abs(enemy_body.getLinearVelocity().x) < 2.27f)
							{
								if(Math.abs(enemy_body.getLinearVelocity().x) <= 1.6f)
								{
									if(!Main_Unit.is_intro_mode)
										enemy_body.applyLinearImpulse(-enemy_speed*1.06f, 0f, enemy_position.x, enemy_position.y, true);
								}
							}		
						}
			}
	    if(enemy_body.getPosition().x > player_body.getPosition().x && distance_enemy_player > 3.8f)			 
	    	e_movement = "standing_left";
	   	}		
	}	
			if(life<2f || enemy_position.y < -100/100f)	
			{
				is_enemy_dead = true;
			}
			
			final_enemy_position_x = enemy_position.x;
			final_enemy_position_y = enemy_position.y;			
			
			if(is_enemy_dead)
			{			
				Draw_Enemy_lost(e_camera);				
//				if(enemy_position.x > player_position.x && is_feinting_down && GamePlayScreen.can_enemies_feint)
//				enemy_body.setTransform(new Vector2(enemy_body.getPosition().x+6/100f,enemy_body.getPosition().y+3/100f),1.5708f);
//				if(enemy_position.x < player_position.x && is_feinting_down && GamePlayScreen.can_enemies_feint)
//				enemy_body.setTransform(new Vector2(enemy_body.getPosition().x-7f/100f,enemy_body.getPosition().y+3/100f),1.5708f);				
			}			
			if(!is_enemy_dead)
			{
				Draw_Enemy(e_camera, e_DeltaTime);			
			}	 
	}
	public void CheckBareerContact(ArrayList<Rectangle> barreers)
	{
		int barreer_contact_index = -1;
		for (Rectangle b : barreers) {			
			if(border_enemy.overlaps(b))
				barreer_contact_index = barreers.indexOf(b);		
		}	
		if(barreer_contact_index !=-1)
			barreer_contact = true;
		else 
			barreer_contact = false;
	}
}
