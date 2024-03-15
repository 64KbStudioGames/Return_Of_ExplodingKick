package com.xkick;

import java.util.ArrayList;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
import com.bitmaps.Animations;
import com.bitmaps.Background;
import com.gui.Extra_Gui_Elements;
import com.gui.Pause_Menu;
import com.logic.Enemy;
import com.logic.GameController;
import com.logic.GameStarter;
import com.logic.Game_Elements;
import com.logic.Shuriken;
import com.logic.SoundProcessing;
import com.logic.UI;
import com.objects.Diamonds;
import com.objects.Extra_Energy;
import com.physics.Collusion_Filtering;
import com.physics.Contact_Listener_Player;
import com.physics.Dynamic_Platform;
import com.physics.Dynamic_objects;

public class Main_Unit implements Screen 
{
	Player player;
	// Various Variables  
    GameStarter game;
		// Set the Game world and the camera
	OrthographicCamera camera;
    public static float GAME_WORLD_WIDTH =  900;
	public static float GAME_WORLD_HEIGHT=  540;	
	StretchViewport game_viewport;	
	int device_width;
	int device_height;
		//Set the diamonds...
	ArrayList<Body> diamond_bodies;
	ArrayList<Diamonds> diamond_objects;
	Texture diamond_tx; 
	SpriteBatch diamond_sb;
	Texture tx_checkpoint_fail;
	SpriteBatch sb_checkpoint_fail;
	Texture tx_checkpoint_pass;
	ArrayList<Vector2> checkpoint_positions;
	ArrayList<Rectangle> diamond_borders;
	public static int in_level_collected_diamonds;
	Body diamond_body;		
	//Set The Hearts
	ArrayList<Body> heart_bodies;
	ArrayList<Extra_Energy> extra_energy_objects;
	ArrayList<Rectangle> heart_borders;
	Body heart_body;	
		//Set the dynamic boxes
	ArrayList<Body> dynamic_object_bodies;
	Texture dynamic_object_tx;
	SpriteBatch dynamic_object_sb;
	ArrayList<Dynamic_objects> dynamic_objects_objects;
	static ArrayList<Rectangle> dynamic_object_borders;
	Body dynamic_object_body;
	PolygonShape dynamic_object_shape;
		// Set the character
	SpriteBatch batch_character;
	Vector2 character_position;
	Rectangle  feet_box,body_box;
	    //Enemies   
    ArrayList<Enemy> enemies;	
	int number_of_enemies;
	int number_of_destroyed_enemies;	
	int enemy_index;

	Vector2 temp_enemy_position;
	float enemy_speed;
	float enemy_jump_power;
	String enemy_direction;	
	ArrayList<Rectangle> e_floor;	
	//The Main Movement of the Character
	public float character_acceleration;
	public static String movement;
	float character_speed_limit;		
	//Switches
	Boolean enable_directional_buttons;	
	Boolean _is_kick_pressed;
	public static Boolean enable_kick_button; 
	Boolean enable_jump_button;	
	 public static Boolean is_player_getting_damage;
	 public static String  damage_direction;
	 int enemy_force_limit;	 
	 int count_of_kicks;
	 //Animation
	 Animations d_animation;
	 public static String type_of_movement;	 
		// Controller
    private GameController d_joy_pad;  
    	//BoX 2D
    private World world;
    private Box2DDebugRenderer b2dr;
    PolygonShape character_shape;
    PolygonShape enemy_shape;
    PolygonShape polygon_shape;  
    PolygonShape diamond_shape;
    PolygonShape heart_shape;
    PolygonShape shuriken_shape;
    PolygonShape checkpoint_shape;
    PolygonShape dynamic_platform_shape;
    Body b2dbody;
    Body[] enemy_bodies; 
    Body baseAndwall_body;
    ArrayList<Rectangle> traps;
    Body different_shape_platforms_body;
    Body box_shaped_platforms_body;
    Body[] diamonds_bodies;  
    ArrayList<Body> checkpoint_bodies;
    Body checkpoint_body;
    int[] checkpoint_indexx;
    int[] dynamic_platform_indexx;
      	//Level and Energy
    UI ui;
    	//Map
    TiledMap tiled_map; 
    OrthogonalTiledMapRenderer map_renderer;
    ArrayList<Rectangle> wall_bounds; 
    Boolean end_of_level;
    Vector2 exit_position;
    Rectangle exit_rectangle;
    Vector2 boss_zone_position; 
    Rectangle boss_zone_rectangle;
    Boolean is_automatic_walk_in_the_boss_zone; 

    	//JumpDetect  
    public static Contact_Listener_Player d_contact_listener;  
    	//Sound
    Sound sound_fx_diamond;
    Sound sound_fx_heart;
    Sound sound_fx_enemy_lost;
    Sound sound_fx_character_lost;
    Sound sound_fx_kick_wind;
    Sound sound_fx_kicking_enemy;
    SoundProcessing sound_processor;
    private  int counter_right_kick, counter_right_punch, counter_special_right,
    counter_left_kick,counter_left_punch,counter_special_left;
    int kick_wind_counter;
    public static Sound sound_fx_player_being_hit;
    int counter_sound_fx_getting_damage;
    int counter_diamond_sound_fx;
    long id_kicking_enemy;

    public static Boolean is_intro_mode;
    float intro_wait_time;
    
    //Talk baloons
    SpriteBatch player_talk_baloon_sprite;
	Texture player_talk_baloon_texture;
    
    	//Hit
    Boolean is_hitting;
    final float minimum_distance = 1.24f;   
    float kick_speed_limiter = 6f;  
    Boolean can_player_jump;    
    	//Dynamic physics...     
    static Rectangle char_dob;
    static Rectangle dob; 
    static Boolean can_player_jump_on_dob;
        
    //Player lost control 
    public static Boolean is_player_alive;
    int lose_sound_time_counter;
    
    //Frame per second - Box2d optimization
    float jump_force_character;
    
    //Shurikens  
    ArrayList<Shuriken> shurikens;
    Body[] shuriken_bodies;
    int shuriken_timer;
    
    //Dynamic Platforms
    ArrayList<Body> dynamic_platformBodies;  
    ArrayList<Rectangle> border_of_dynamic_platforms;
    Body dynamic_platform_body;
    Boolean on_sliding_platform;  
    ArrayList<Rectangle> dpp_starts;
    ArrayList<Rectangle> dpp_stops;
    String platform_direction;
    ArrayList<Dynamic_Platform> dpp_objects;    
    int number_of_dpp_stepped;
    
    Boolean enemies_already_disposed;
    int timer_shift_screens;
    int wait_for_dispose;    
    
    
    public static String g_controller_type; 
    
    Pause_Menu pause_m;
    InputMultiplexer ip_mx;
    
    String platform_names;    
    
    float red;
    float green;
    float blue;
    
   public static Boolean is_flip_kicking;
   public static Boolean can_enemies_feint;   
 
   Vector2 player_start_position;  
   Boolean in_the_boss_zone;
   Boolean is_fighting_with_the_boss;
   
   Boolean player_wins;
   int time_of_playing_win_animation;
   Boolean finished_playing_win_animation;        
   
   Music level_music,boss_zone_music, boss_music, win_music;
   
   Background bg;
   String ground_type;
   ShapeRenderer shape_renderer;
   Rectangle rect_ground_and_walls;
   ArrayList<Rectangle> borders_of_different_shapes;
   Boolean different_platform_overlap, dynamic_platform_overlap, 
   overlap_for_friction, is_on_the_edge;
   int counter_edge_stand;
   //label fix
   Extra_Gui_Elements extra_gui_element;
   //player size and position fix
   Vector2 player_size; 
   Vector2[] check_passes;
   
   Boolean did_finish_the_level; 
   
   ArrayList<Rectangle> barreers;
   ArrayList<Body> barreer_bodies;
   PolygonShape barreer_shape;
   final Vector2 default_player_size = new Vector2(20f/10f,20.5f/10f);
   
    public Main_Unit(GameStarter Game)
    {
    	this.game = Game;  
    	this.can_player_jump = true;    	
    	this.e_floor = new ArrayList<Rectangle>();    
    	//game.game_level_name = "Level_maps/world_1_map.tmx";
    } 
    
	@Override
	public void show ()
	{	
		if(Game_Elements.selected_character != Game_Elements.characters[2])
			player_size = default_player_size;
		else 
			player_size = new Vector2(default_player_size.x*1.095f,default_player_size.y*1.135f);
		Game_Elements.game_paused = false;	
		enemies_already_disposed = false;
		timer_shift_screens = 0;
		wait_for_dispose = 0;
		in_the_boss_zone = false;	
		
	   d_animation = new Animations();
	   count_of_kicks = 0;
	   //2.425 is a good value for the speed limit.
	   character_speed_limit = 2.125f;
	   //jump_force_character =  90f;
	   character_acceleration = 21.5f;	  
	   // This is for keeping the character look right for the first time that the game starts...
	   movement = "right";
	   type_of_movement = "static_movement";
	   _is_kick_pressed = false;
	   enable_kick_button = true;	
	   enable_jump_button = true;	   
	   //parameters of functions for player getting damage...
	   is_player_getting_damage = false;
	   damage_direction = "no_damage";
	   enemy_force_limit = 0;
	   lose_sound_time_counter = 0;
	   counter_sound_fx_getting_damage = 0;
	   
	   //Level Arrangement.LvL_index
	   end_of_level = false;
	   exit_position = new Vector2();
	   exit_rectangle = new Rectangle();
	   //Boss zone in the level...
	   boss_zone_position = new Vector2();
	   boss_zone_rectangle = new Rectangle();	   
	   
	   if(Game_Elements.Level_data == 0)
		   Game_Elements.Level_data	 =1;    

	   tiled_map = new TmxMapLoader().load("Level_maps/world_" + Game_Elements.GetLevelData() + "_map.tmx");
		map_renderer = new OrthogonalTiledMapRenderer(tiled_map,1/100f);
		
	    // Set the camera and the aspect ratio...
	  
	 	camera = new OrthographicCamera();
	    game_viewport = new StretchViewport((GAME_WORLD_WIDTH)/100f,(GAME_WORLD_HEIGHT)/100f,camera);
	    game_viewport.apply();		
	    //Set the enemies...
        enemy_bodies = new Body[8];
	    enemies = new ArrayList<Enemy>();  
	    
	    number_of_destroyed_enemies = 0;
	    SetTheEnemies(); 	
	   //The Shurikens...
        shuriken_timer = 0;
	    shuriken_bodies = new Body[3];
	    shurikens = new ArrayList<Shuriken>();
	   
	    //Set the diamonds...
	    diamond_bodies = new ArrayList<Body>();
	    diamond_borders = new ArrayList<Rectangle>();
	    diamond_objects = new ArrayList<Diamonds>();	
	    counter_diamond_sound_fx = 0;	   
	    in_level_collected_diamonds = Game_Elements.total_diamonds;	  
	    //Set the hearts...
	    heart_bodies = new ArrayList<Body>();
	    heart_borders = new ArrayList<Rectangle>();
	    extra_energy_objects = new ArrayList<Extra_Energy>();	    
	    //dynamic platforms	   
	    dynamic_platformBodies = new ArrayList<Body>();	  
	    border_of_dynamic_platforms = new ArrayList<Rectangle>();
	    dpp_objects = new ArrayList<Dynamic_Platform>();
	    on_sliding_platform = false;
	    dpp_starts = new ArrayList<Rectangle>();
	    dpp_stops = new ArrayList<Rectangle>();
	    platform_direction = "plt_right";
	    number_of_dpp_stepped = 0;
	    //Set the checkpoints...
	    checkpoint_bodies = new ArrayList<Body>();	 
	    //Set the traps
	    traps = new ArrayList<Rectangle>();
	    //Set the dynamic objects
	    dynamic_object_bodies = new ArrayList<Body>();
	    dynamic_object_borders = new ArrayList<Rectangle>();
	    dynamic_objects_objects = new ArrayList<Dynamic_objects>();	  
	    char_dob = new Rectangle();
	    dob = new Rectangle();
	    can_player_jump_on_dob = true;
	    can_player_jump = true;	    
	    ///// BOX2D HERE !!!
	    world = new World(new Vector2(0,-9.81f),true);
	    d_contact_listener = new Contact_Listener_Player();
		world.setContactListener(d_contact_listener);
		b2dr = new Box2DDebugRenderer();
	    SetBox2Ditems();	    										  
	    //Set the Items like diamonds and etc...
	    SetTheDiamonds();
	    //Set the hearts...
	    SetTheHearts();
	    //Set the Dynamic Objects...
	    SetTheDynamicObjects();	    
	    set_Shurikens();
	    SetTheCheckPoints();
	    player_start_position = new Vector2(5.9f,6f);
	    
	    //For quick test purpose...(Brings the player to the boss directly)
	 	//Game_Elements.boss_reached = true;
	    if(Game_Elements.last_point!=null)
	    ReturnBack();
	    	//b2dbody.setTransform(Game_Elements.last_point.x,b2dbody.getPosition().y,0);
		if(Game_Elements.boss_reached)
			b2dbody.setTransform(136,b2dbody.getPosition().y,0);
	    
	    //Continue after loss
	    Game_Elements.check_1_passed = false;
	    Game_Elements.check_2_passed = false;
	    Game_Elements.check_0_passed = false;
	    Game_Elements.check_none = false;	   
		Load_CheckPoints_For_The_Player();	
	    //Set the Animations...	    	   
	    enable_directional_buttons =  true;	   
	    d_animation.Animate_All();
	    is_hitting = false;
	    batch_character = new SpriteBatch(); 
	    body_box = new Rectangle();	 	        
	     // Set time and energy...	  
	    ui = new UI(camera, GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
	    ui.Animate_Diamond_Img();
	    //Set the controllers...
	    d_joy_pad = new GameController(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);	  
	    
	    if(Game_Elements.controller_type != null)
	        g_controller_type = Game_Elements.controller_type;
	    //The default controller...   
	    if(Game_Elements.controller_type == null)
	        g_controller_type = "JoyPad";	
    	
	    //Audio ...    
	    sound_fx_diamond = Gdx.audio.newSound(Gdx.files.internal("Audio/diamond_collect.wav"));
	    sound_fx_enemy_lost = Gdx.audio.newSound(Gdx.files.internal("Audio/enemy_lost.wav"));
	    sound_fx_character_lost = Gdx.audio.newSound(Gdx.files.internal("Audio/character_lost_fx.wav"));
	    sound_fx_heart = Gdx.audio.newSound(Gdx.files.internal("Audio/heart_collected.wav"));
	    sound_fx_player_being_hit = Gdx.audio.newSound(Gdx.files.internal("Audio/enemy_hitting_player.wav"));
	    sound_fx_kick_wind = Gdx.audio.newSound(Gdx.files.internal("Audio/kick_wind.wav"));
	    sound_fx_kicking_enemy = Gdx.audio.newSound(Gdx.files.internal("Audio/enemy_hurt.wav"));	    
	    counter_right_kick = 0;counter_right_punch =0;counter_special_right = 0;
	    counter_left_kick = 0;counter_left_punch = 0;counter_special_left = 0;
	    kick_wind_counter = 0;
	    id_kicking_enemy = 0;  
	    sound_processor = new SoundProcessing();
	   
	    //MUSIC ITEMS
	    level_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_level0"+ Game_Elements.GetLevelData()+".ogg" ));
	    level_music.setVolume(0.885f);
	    level_music.setLooping(true);
	    try {	    
	    //level_music.play();
	    }
	    catch(Exception ex)
	    {	  
	    	level_music = null;
	    	level_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_level0"+ Game_Elements.GetLevelData() +".ogg" ));		
	    	level_music.play();
	    }
        boss_zone_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_boss_zone.ogg"));
        boss_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_boss.ogg"));
        win_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_win.ogg"));	
        boss_music.setVolume(0.855f);
        win_music.setVolume(0.795f);
	    is_intro_mode = false;
	    is_automatic_walk_in_the_boss_zone = false;
	    intro_wait_time = 0;	    
	    //Talk baloons...
	    this.player_talk_baloon_sprite = new SpriteBatch();
        this.player_talk_baloon_texture = new Texture(Gdx.files.internal("Game_Art/Talk_Baloons/player_baloons/player_talk_baloon_0" + Game_Elements.GetLevelData() + ".png"));        
        pause_m = new Pause_Menu();
        ip_mx =new InputMultiplexer();         
        ip_mx.addProcessor(pause_m.logic_stage_pause_menu); 	 
        ip_mx.addProcessor(d_joy_pad.stage_controller);	       
	    dynamic_platform_indexx = new int[tiled_map.getLayers().get("DynamicPlatforms").getObjects().getCount()];
	    platform_names ="";
	    for(int i=0;i<tiled_map.getLayers().get("DynamicPlatforms").getObjects().getCount();i++)
	    {
	    	platform_names += String.valueOf(tiled_map.getLayers().get("DynamicPlatforms").getObjects().getIndex("dp_0" + i));
	    }	
	   red = 0;
	   green =0;
	   blue =0;	   
	   is_flip_kicking = false;
	   can_enemies_feint = false;
	   is_fighting_with_the_boss = false;	 
	   player_wins = false;
	   time_of_playing_win_animation = 0;
	   finished_playing_win_animation = false;
	   // the original value for camera zoom is 1.525f
	   camera.zoom =1.525f;	   
	   bg = new Background(Game_Elements.GetLevelData(), camera);
	   bg.SetCloudAnimation();
	   ground_type = "null ground";	  
	   shape_renderer = new ShapeRenderer();	  
	   different_platform_overlap = false;
	   dynamic_platform_overlap = false;
	   overlap_for_friction = false;
	   is_on_the_edge = false;
	   counter_edge_stand = 0;	
	   
	   extra_gui_element = new Extra_Gui_Elements(ui.ui_viewport.getCamera(),
			   new Vector2(ui.label_stage_clear.getX()-80,ui.label_stage_clear.getY()-30),
			   new Vector2(ui.label_stage_clear.getWidth()*4.625f, ui.label_stage_clear.getHeight()*1.5f));
	   extra_gui_element.SetStageClearBackground();	
	   
	   did_finish_the_level = false;
	   player = new Player();	 
	   if(Game_Elements.selected_character.equals("characterSheet00"))
		  player_size.set(player_size.x * 1.0675f,player_size.y*1.0625f);
	   if(Game_Elements.selected_character.equals("characterSheet01"))
			  player_size.set(default_player_size); 
	   feet_box = new Rectangle(0,0,0,0);
	   body_box = new Rectangle(0,0,0,0);
	   
//	   System.out.println("player size x :" + player_size.x + "player size y: " + player_size.y + "\n" 
//		  + Game_Elements.selected_character);
	}	
	
	public void SetBox2Ditems()
	{
     // The Elements
     BodyDef bdef = new BodyDef();  
     polygon_shape = new PolygonShape();
     FixtureDef fdef_character_body = new FixtureDef();
     FixtureDef fdef_enemy_body = new FixtureDef();
     FixtureDef fdef_player_foot = new FixtureDef();
     FixtureDef fdef_objects = new FixtureDef();  
     FixtureDef fdef_diamond_body = new FixtureDef();
     FixtureDef fdef_heart_body = new FixtureDef();
     FixtureDef fdef_checkpoint_body = new FixtureDef();
     FixtureDef fdef_dynamic_object_body = new FixtureDef();
     FixtureDef fdef_shuriken_body= new FixtureDef();   
     FixtureDef fdef_dynamic_platform_body = new FixtureDef();
     FixtureDef fdef_bareer = new FixtureDef();
     // THE CHARACTER BODY  
     if(Game_Elements.GetLevelData() == 5)
         bdef.position.set(650f/100,410/100);
     else
    	 bdef.position.set(590f/100,410/100);     
     
     player_start_position = bdef.position;
     bdef.type = BodyType.DynamicBody;
     b2dbody = world.createBody(bdef); 
     character_shape = new PolygonShape();     
     character_shape.setAsBox(32f/100f, 67/100f);
     fdef_character_body.shape = character_shape; 
     fdef_character_body.filter.categoryBits = Collusion_Filtering.BIT_PLAYER;
     fdef_character_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS | Collusion_Filtering.BIT_DYNAMIC_OBJECTS
    		 | Collusion_Filtering.BIT_ENEMY | Collusion_Filtering.BIT_SHURIKEN | Collusion_Filtering.BIT_CHECKPOINTS | Collusion_Filtering.BIT_DYNAMIC_PLATFORMS;
     fdef_character_body.density = 102f;
     fdef_character_body.friction = 1f;    
     fdef_character_body.isSensor = false;
     character_position  = new Vector2(b2dbody.getPosition().x-108/100f,b2dbody.getPosition().y-87/100f);
     b2dbody.createFixture(fdef_character_body).setUserData("character_body");
     // THE CHARACTER FOOT
     PolygonShape  character_foot = new PolygonShape();
     character_foot.setAsBox(9/100f, 9/100f, new Vector2(0,-78/100f),0);
    // CircleShape character_foot = new CircleShape();
     //character_foot.setRadius(9/100f);
     //character_foot.setPosition(new Vector2(0,-75/100f));
     fdef_player_foot.shape = character_foot;
     fdef_player_foot.filter.categoryBits = Collusion_Filtering.BIT_PLAYER_FOOT;
     fdef_player_foot.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS;
     fdef_player_foot.isSensor = true;        
     b2dbody.createFixture(fdef_player_foot).setUserData("character_foot");     
     b2dbody.setFixedRotation(true);    
      /// THE ENEMIES...	
     //also for the non-boss enemies...
     bdef.type = BodyType.DynamicBody;     
     Rectangle rect_enemy_location = null;
     enemy_index = -1;
     for(MapObject map_object : tiled_map.getLayers().get("Enemy_Location").getObjects().getByType(RectangleMapObject.class))
     {
    	 enemy_index +=1;
    	 rect_enemy_location = ((RectangleMapObject) map_object).getRectangle();
    	 bdef.position.set(rect_enemy_location.getX()/100f,rect_enemy_location.getY()/100f);
    	 enemy_bodies[enemy_index] = world.createBody(bdef);  
     }     
     enemy_shape = new PolygonShape();     
     enemy_shape.setAsBox(38/100f, 67/100f); 
     
     fdef_enemy_body.shape  = enemy_shape;
     fdef_enemy_body.filter.categoryBits = Collusion_Filtering.BIT_ENEMY;
     fdef_enemy_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS | Collusion_Filtering.BIT_DYNAMIC_OBJECTS 
    		 | Collusion_Filtering.BIT_ENEMY | Collusion_Filtering.BIT_DYNAMIC_PLATFORMS | Collusion_Filtering.BIT_BARREER;
     fdef_enemy_body.density = 40f;
     fdef_enemy_body.friction = 0.1f;
     for(int k=0;k<enemy_bodies.length;k++)
     {
    	 enemy_bodies[k].createFixture(fdef_enemy_body);
    	 enemy_bodies[k].setFixedRotation(true);
     }  
     
   /// GROUND AND THE WALLS
     e_floor.clear();
     rect_ground_and_walls = null;   
     for(MapObject map_object : tiled_map.getLayers().get("Borders").getObjects().getByType(RectangleMapObject.class))
    {
    	rect_ground_and_walls = ((RectangleMapObject) map_object).getRectangle();    
    	e_floor.add(new Rectangle(rect_ground_and_walls.x/100f,(rect_ground_and_walls.y+8)/100f,rect_ground_and_walls.width/100f,rect_ground_and_walls.height/100f));
    	bdef.type = BodyType.StaticBody;
    	bdef.position.set((rect_ground_and_walls.getX() +rect_ground_and_walls.getWidth()/2)/100f,(rect_ground_and_walls.getY() + rect_ground_and_walls.getHeight()/2)/100f);    	
    	polygon_shape.setAsBox(rect_ground_and_walls.getWidth()/2/100f, rect_ground_and_walls.getHeight()/2/100f);
    	fdef_objects.shape = polygon_shape;
    	fdef_objects.filter.categoryBits = Collusion_Filtering.BIT_GROUND_WALLS;
    	fdef_objects.filter.maskBits = Collusion_Filtering.BIT_PLAYER_FOOT | Collusion_Filtering.BIT_PLAYER
    			| Collusion_Filtering.BIT_ENEMY | Collusion_Filtering.BIT_DIAMONDS | Collusion_Filtering.BIT_DYNAMIC_OBJECTS
    			| Collusion_Filtering.BIT_SHURIKEN | Collusion_Filtering.BIT_CHECKPOINTS;
    	fdef_objects.friction = 1f;
    	baseAndwall_body = world.createBody(bdef);
        baseAndwall_body.createFixture(fdef_objects).setUserData("f_ground");          
    }
    
    /// DIFFERENT SHAPE PLATFORM ELEMENTS
     //int how_many_different_shapes_from_the_map = 0;
     borders_of_different_shapes = new ArrayList<Rectangle>();	   
    for(MapObject map_object : tiled_map.getLayers().get("Obstacles").getObjects().getByType(PolygonMapObject.class))
    {
    	//how_many_different_shapes_from_the_map +=1;
    	Polygon polygon = ((PolygonMapObject) map_object).getPolygon();
    	bdef.type = BodyType.KinematicBody;
    	bdef.fixedRotation = false;
    	
    	float[] polygon_vertices = new float[polygon.getVertices().length];
        
    	for(int i=0;i<polygon.getVertices().length;i++)
    	{
    		polygon_vertices[i] = polygon.getVertices()[i]/100f;
    	}    	
    	polygon.setVertices(polygon_vertices);    	
    	polygon_shape.set(polygon_vertices);    	
    	bdef.position.set((polygon.getX())/100f,(polygon.getY())/100f);    	
    	fdef_objects.shape = polygon_shape;
    	fdef_objects.filter.categoryBits =  Collusion_Filtering.BIT_GROUND_WALLS;
    	fdef_objects.filter.maskBits     =  Collusion_Filtering.BIT_PLAYER_FOOT | Collusion_Filtering.BIT_PLAYER 
    			| Collusion_Filtering.BIT_ENEMY | Collusion_Filtering.BIT_DIAMONDS 
    			| Collusion_Filtering.BIT_DYNAMIC_OBJECTS | Collusion_Filtering.BIT_CHECKPOINTS;
    	fdef_objects.friction = 1f;
    	fdef_objects.density = 100f;
    	different_shape_platforms_body = world.createBody(bdef);
    	
    	if(Game_Elements.Level_data != 6 && Game_Elements.Level_data != 7 )
    	borders_of_different_shapes.add(new Rectangle(different_shape_platforms_body.getPosition().x+22/100f,
    			different_shape_platforms_body.getPosition().y-10/100f,275/100f,120/100f));
    	else 
    		borders_of_different_shapes.add(new Rectangle(different_shape_platforms_body.getPosition().x-21/100f,
        			different_shape_platforms_body.getPosition().y-20/100f,360/100f,120/100f));
    	
    	different_shape_platforms_body.createFixture(fdef_objects).setUserData("f_triangle_ground"); 
    } 
   /// BOX SHAPED PLATFORM ELEMENTS    
    	for(MapObject map_object : tiled_map.getLayers().get("Obstacles").getObjects().getByType(RectangleMapObject.class))
    	{
    		Rectangle rect = ((RectangleMapObject) map_object).getRectangle(); 
    		e_floor.add(new Rectangle(rect.x/100f,(rect.y+8)/100f,rect.width/100f,rect.height/100f));
    		bdef.type = BodyType.StaticBody;
    		bdef.position.set((rect.getX() +rect.getWidth()/2)/100f,(rect.getY() + rect.getHeight()/2)/100f);    	
    		polygon_shape.setAsBox(rect.getWidth()/2/100f, rect.getHeight()/2/100f);
    		fdef_objects.shape = polygon_shape;    	
    		fdef_objects.filter.categoryBits =  Collusion_Filtering.BIT_GROUND_WALLS;
    		fdef_objects.filter.maskBits 	 = 
    				   Collusion_Filtering.BIT_PLAYER_FOOT 
    				| Collusion_Filtering.BIT_PLAYER 
    				| Collusion_Filtering.BIT_ENEMY 
    				| Collusion_Filtering.BIT_DIAMONDS 
    				| Collusion_Filtering.BIT_DYNAMIC_OBJECTS
    				| Collusion_Filtering.BIT_CHECKPOINTS;
    		fdef_objects.friction = 1f;
    		box_shaped_platforms_body = world.createBody(bdef);
    		box_shaped_platforms_body.createFixture(fdef_objects).setUserData("f_ground");
    	} 
    	 	/// GET THE EXIT DOOR!
    	for(MapObject map_object : tiled_map.getLayers().get("Exit").getObjects().getByType(RectangleMapObject.class))
    	{
    		exit_position.x = ((RectangleMapObject) map_object).getRectangle().x/100f;
    		exit_position.y = ((RectangleMapObject) map_object).getRectangle().y/100f;
    		exit_rectangle = ((RectangleMapObject) map_object).getRectangle();
    	}	   
    	
    	//dpp starts 	
    	Rectangle dpp_start_rectangle;
    	for(MapObject map_object : tiled_map.getLayers().get("DP_starts").getObjects().getByType(RectangleMapObject.class))
    	{
    		dpp_start_rectangle = new Rectangle( ((RectangleMapObject) map_object).getRectangle().x/100f,((RectangleMapObject) map_object).getRectangle().y/100f,30/100f,30/100f);
    	    dpp_starts.add(dpp_start_rectangle);
    	}
    	//dpp stops 	
    	Rectangle dpp_stop_rectangle;
    	for(MapObject map_object : tiled_map.getLayers().get("DP_stops").getObjects().getByType(RectangleMapObject.class))
    	{
    		dpp_stop_rectangle = new Rectangle( ((RectangleMapObject) map_object).getRectangle().x/100f,((RectangleMapObject) map_object).getRectangle().y/100f,30/100f,30/100f);
    	    dpp_stops.add(dpp_stop_rectangle);
    	}	
    	
    	//barreers
    	barreers = new ArrayList<Rectangle>();
    	barreer_bodies = new ArrayList<Body>();
    	Rectangle barreer_rectangle;  
    	Body barreer_body;
    	barreer_shape = new PolygonShape();
    	for(MapObject map_object : tiled_map.getLayers().get("Barreers").getObjects().getByType(RectangleMapObject.class))
    	{
    		barreer_rectangle = new Rectangle( ((RectangleMapObject) map_object).getRectangle().x/100f,((RectangleMapObject) map_object).getRectangle().y/100f,
    				((RectangleMapObject) map_object).getRectangle().getWidth()/100f,
    				((RectangleMapObject) map_object).getRectangle().getHeight()/100f);
    		barreers.add(barreer_rectangle);
    		bdef.type = BodyType.StaticBody;    		
    	    bdef.position.set(barreer_rectangle.getX() + barreer_rectangle.getWidth()/2f, 
    	    		barreer_rectangle.getY()+barreer_rectangle.getHeight()/2f);
    	    barreer_shape.setAsBox(barreer_rectangle.getWidth()/2f, barreer_rectangle.getHeight()/2f);
    	    fdef_bareer.shape = barreer_shape;
    	    fdef_bareer.filter.categoryBits = Collusion_Filtering.BIT_BARREER;
    	    fdef_bareer.filter.maskBits = Collusion_Filtering.BIT_ENEMY | Collusion_Filtering.BIT_GROUND_WALLS;
    	    fdef_bareer.friction = 1f;
    	    barreer_body = world.createBody(bdef);
    	    barreer_body.createFixture(fdef_bareer);
    	    barreer_bodies.add(barreer_body);    	
    	}
    	
    	
    	
     	/// TRAPS... 
    	Rectangle trap_rectangle = new Rectangle();
    	for(MapObject map_object : tiled_map.getLayers().get("Traps").getObjects().getByType(RectangleMapObject.class))
    	{
    		trap_rectangle = ( (RectangleMapObject) map_object).getRectangle();
    	    traps.add(new Rectangle(trap_rectangle.x/100f+10/100f,(trap_rectangle.y+8)/100f,(trap_rectangle.getWidth()-20)/100f,trap_rectangle.getHeight()/100f));   	   
    	}	    	
    	    	
    	// DIAMONDS
    	bdef.type = BodyType.StaticBody;     
        Rectangle rect_diamond_location = null;
        for(MapObject map_object : tiled_map.getLayers().get("Diamonds").getObjects().getByType(RectangleMapObject.class))
        {       	 
       	 rect_diamond_location = ((RectangleMapObject) map_object).getRectangle();
       	 bdef.position.set(rect_diamond_location.getX()/100f,rect_diamond_location.getY()/100f);
       	 
       	 diamond_body = world.createBody(bdef);
         diamond_bodies.add(diamond_body);  
        }
        diamond_shape = new PolygonShape();     
        diamond_shape.setAsBox(20/100f, 20/100f);         
        fdef_diamond_body.shape  = diamond_shape;       
        fdef_diamond_body.filter.categoryBits = Collusion_Filtering.BIT_DIAMONDS;
        fdef_diamond_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS;
        fdef_diamond_body.density = 3f;      
    
        
        for(int k=0;k<diamond_bodies.size();k++)
        {
       	 diamond_bodies.get(k).createFixture(fdef_diamond_body);  
       	 diamond_bodies.get(k).setFixedRotation(true);
       	 Rectangle diamond_border = new Rectangle(diamond_bodies.get(k).getPosition().x,diamond_bodies.get(k).getPosition().y,64/100f,64/100f);
       	 diamond_borders.add(diamond_border);
        }      
      
        /////Hearts From The Map...    
    	bdef.type = BodyType.StaticBody;     
        Rectangle rect_heart_location = null;
        for(MapObject map_object : tiled_map.getLayers().get("Hearts").getObjects().getByType(RectangleMapObject.class))
        {       	 
       	 rect_heart_location = ((RectangleMapObject) map_object).getRectangle();
       	 bdef.position.set(rect_heart_location.getX()/100f,rect_heart_location.getY()/100f);       	 
       	 heart_body = world.createBody(bdef);
         heart_bodies.add(heart_body);  
        }
        heart_shape = new PolygonShape();     
        heart_shape.setAsBox(20/100f, 20/100f);         
        fdef_heart_body.shape  = diamond_shape;       
        fdef_heart_body.filter.categoryBits = Collusion_Filtering.BIT_HEARTS;
        fdef_heart_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS;
        fdef_heart_body.density = 3f;         
        for(int k=0;k<heart_bodies.size();k++)
        {
       	 heart_bodies.get(k).createFixture(fdef_heart_body);  
       	 heart_bodies.get(k).setFixedRotation(true);
       	 Rectangle heart_border = new Rectangle(heart_bodies.get(k).getPosition().x,heart_bodies.get(k).getPosition().y,64/100f,64/100f);
       	 heart_borders.add(heart_border);
        }  
        
        //DYNAMIC OBJECTS 
    	bdef.type = BodyType.DynamicBody;     
        Rectangle rect_dynamic_object_location = null;
       
        dynamic_object_shape = new PolygonShape();     
        dynamic_object_shape.setAsBox(30/100f, 30/100f);         
        fdef_dynamic_object_body.shape  = dynamic_object_shape;       
        fdef_dynamic_object_body.filter.categoryBits = Collusion_Filtering.BIT_DYNAMIC_OBJECTS;
        fdef_dynamic_object_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS | Collusion_Filtering.BIT_PLAYER
        		| Collusion_Filtering.BIT_DYNAMIC_OBJECTS | Collusion_Filtering.BIT_ENEMY;
        fdef_dynamic_object_body.density = 20f;
        fdef_dynamic_object_body.restitution = 0;
        fdef_dynamic_object_body.friction = 1;        
        
        for(MapObject map_object : tiled_map.getLayers().get("Dynamic_Objects").getObjects().getByType(RectangleMapObject.class))
        {       	 
       	 rect_dynamic_object_location = ((RectangleMapObject) map_object).getRectangle();
       	 bdef.position.set(rect_dynamic_object_location.getX()/100f,rect_dynamic_object_location.getY()/100f); 
       	 
       	 dynamic_object_body = world.createBody(bdef);       	
       	 dynamic_object_bodies.add(dynamic_object_body);  	
        }       
        for(int k=0;k<dynamic_object_bodies.size();k++)
        {       	 
       	 dynamic_object_bodies.get(k).setFixedRotation(true);
       	 Rectangle dynamic_object_border = new Rectangle(dynamic_object_bodies.get(k).getPosition().x,dynamic_object_bodies.get(k).getPosition().y,20/100f,20/100f);
       	 dynamic_object_borders.add(dynamic_object_border);
       	 e_floor.add(dynamic_object_border);
       	 dynamic_object_bodies.get(k).createFixture(fdef_dynamic_object_body).setUserData("f_ground");
        }
        //Dynamic Platforms      
     	bdef.type = BodyType.KinematicBody;     
         Rectangle rec_dyn_plt = new Rectangle();
         for(MapObject map_object : tiled_map.getLayers().get("DynamicPlatforms").getObjects().getByType(RectangleMapObject.class))
         {       	 
        	 rec_dyn_plt = ((RectangleMapObject) map_object).getRectangle();
        	 bdef.position.set((rec_dyn_plt.getX()+rec_dyn_plt.getWidth()/2)/100f,(rec_dyn_plt.getY() + rec_dyn_plt.getHeight()/2)/100f);
        	 
        	 dynamic_platform_body = world.createBody(bdef);         	
        	 dynamic_platformBodies.add(dynamic_platform_body);  
        	 //gel la buraya
        	 border_of_dynamic_platforms.add(new Rectangle( rec_dyn_plt.getX()/100f, rec_dyn_plt.getY()/100f,
        			 rec_dyn_plt.getWidth()*0.985f/100f,rec_dyn_plt.getHeight()/100f )  );
        	 dpp_objects.add(new Dynamic_Platform(dynamic_platform_body.getPosition(), new Vector2(rec_dyn_plt.getWidth()/100f,rec_dyn_plt.getHeight()/100f) ) );
         }
         dynamic_platform_shape = new PolygonShape();     
         dynamic_platform_shape.setAsBox(rec_dyn_plt.width/100f/1.675f, rec_dyn_plt.height/100f/2);   
         fdef_dynamic_platform_body.shape  = dynamic_platform_shape;       
         fdef_dynamic_platform_body.filter.categoryBits = Collusion_Filtering.BIT_DYNAMIC_PLATFORMS;
         fdef_dynamic_platform_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS | Collusion_Filtering.BIT_PLAYER | Collusion_Filtering.BIT_ENEMY;
         fdef_dynamic_platform_body.friction = 1f;
         fdef_dynamic_platform_body.density = 80f; 
         
         for(int k=0;k<dynamic_platformBodies.size();k++)
         {
        	 dynamic_platformBodies.get(k).createFixture(fdef_dynamic_platform_body).setUserData("f_dynamic_ground");        
        	 dynamic_platformBodies.get(k).setFixedRotation(true);  
         } 
        
        //Boss zone
	 	/// GET THE EXIT DOOR!
    	for(MapObject map_object : tiled_map.getLayers().get("Boss_Zone").getObjects().getByType(RectangleMapObject.class))
    	{
    		boss_zone_position.x = ((RectangleMapObject) map_object).getRectangle().x/100f;
    		boss_zone_position.y = ((RectangleMapObject) map_object).getRectangle().y/100f; 
    		boss_zone_rectangle.set(boss_zone_rectangle.x, boss_zone_rectangle.y,boss_zone_rectangle.width,boss_zone_rectangle.height);
    	}	 
        
    	for(int z=0;z<enemy_bodies.length;z++)
    	{     		
    		enemies.get(z).SetEnemyPhysics(enemy_bodies[z],camera, world);
    	}    	
    	// The Shuriken Physics...    	
        bdef.position.set(1000,1000);
        bdef.type = BodyType.DynamicBody;
        for(int j=0;j<shuriken_bodies.length;j++)
    	{
        	shuriken_bodies[j] = world.createBody(bdef); 
    	}
        shuriken_shape = new PolygonShape();     
        shuriken_shape.setAsBox(30/100f, 30/100f);
        fdef_shuriken_body.shape = shuriken_shape; 
        fdef_shuriken_body.filter.categoryBits = Collusion_Filtering.BIT_SHURIKEN;
        fdef_shuriken_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS | Collusion_Filtering.BIT_DYNAMIC_OBJECTS;
        fdef_shuriken_body.density = 20f;
        fdef_shuriken_body.friction = 0f;
      
        for(int k=0;k<shuriken_bodies.length;k++)
        {
       	 shuriken_bodies[k].createFixture(fdef_shuriken_body);
       	 shuriken_bodies[k].setFixedRotation(true);
       	 shuriken_bodies[k].setGravityScale(0);
        }       	
        
        //CHECKPOINTs....       
    	bdef.type = BodyType.StaticBody;   	
    	check_passes = new Vector2[3];  
    	int count_map_objects=0;
        Rectangle checkpoint_map_rectangle = new Rectangle();
        ArrayList<Rectangle> map_rectangles = new ArrayList<Rectangle>();
        for(MapObject map_object : tiled_map.getLayers().get("Checkpoints").getObjects().getByType(RectangleMapObject.class))
        {        
       	 checkpoint_map_rectangle = ((RectangleMapObject) map_object).getRectangle();       	 
       	 
       	 bdef.position.set(checkpoint_map_rectangle.getX()/100f,checkpoint_map_rectangle.getY()/100f + checkpoint_map_rectangle.getHeight()/2/100f);
       	map_rectangles.add(new Rectangle(checkpoint_map_rectangle.getX()/100f,
       			checkpoint_map_rectangle.getY()/100f + checkpoint_map_rectangle.getHeight()/2/100f,
       			checkpoint_map_rectangle.getWidth()/100,checkpoint_map_rectangle.getHeight()));
       	 //System.out.println("maaaap" + map_object.getName().toString());
       	 checkpoint_body = world.createBody(bdef);
       	 checkpoint_body.setUserData(map_object.getName().toString());  
       	 count_map_objects++;
         checkpoint_bodies.add(checkpoint_body);        
        }     
        
        for(int n=0;n<count_map_objects;n++)
        {
        	check_passes[n] = new Vector2(new Vector2(map_rectangles.get(n).getX(), map_rectangles.get(n).getY()));        	
        }
        
        checkpoint_shape = new PolygonShape();     
        checkpoint_shape.setAsBox(checkpoint_map_rectangle.width/100f/2, checkpoint_map_rectangle.height/100f/2);   
        fdef_checkpoint_body.shape  = checkpoint_shape;       
        fdef_checkpoint_body.filter.categoryBits = Collusion_Filtering.BIT_CHECKPOINTS;
        fdef_checkpoint_body.filter.maskBits = Collusion_Filtering.BIT_GROUND_WALLS |
        		Collusion_Filtering.BIT_ENEMY | Collusion_Filtering.BIT_PLAYER;
        fdef_checkpoint_body.density = 20f; 
        
        for(int k=0;k<checkpoint_bodies.size();k++)
        {
       	 checkpoint_bodies.get(k).createFixture(fdef_checkpoint_body);  
       	 checkpoint_bodies.get(k).setFixedRotation(true);  
        }      
	}	
	public void SetTheEnemies()
	{	
		for(int i =0;i<8;i++)
		{	
			//default enemy values...
			enemies.add(new Enemy(new Vector2(0,0),new Vector2(1170/100f,410/100f),camera,"Enemy#" + i, Game_Elements.GetLevelData()));
		}
	
		//here comes the new texture
		for(int j =0;j < enemies.size();j++)
		{	
		  enemies.get(j).Animate_Enemy_Lost(j);	
		  enemies.get(j).Animate_Lost_Right();
		  enemies.get(j).Animate_Lost_Left();
		  enemies.get(j).Animate_Enemy_Starter(j);	
		  enemies.get(j).Animate_Enemy_Walking_Right();
		  enemies.get(j).Animate_Enemy_Walking_Left();		  
		  enemies.get(j).Animate_Enemy_Right_Kick();
		  enemies.get(j).Animate_Enemy_Left_Kick();
		  enemies.get(j).alt_Animate_Enemy_Right_Kick();
		  enemies.get(j).Animate_Enemy_Throw_Shuriken_Right();
		  enemies.get(j).Animate_Enemy_Throw_Shuriken_Left();
		  enemies.get(j).alt_Animate_Enemy_Left_Kick();
		  enemies.get(j).Animate_Enemy_Getting_Impact_From_Left();
		  enemies.get(j).Animate_Enemy_Getting_Impact_From_Right();
		  enemies.get(j).Animate_Standing_Right();
		  enemies.get(j).Animate_Standing_Left();
		  enemies.get(j).enemy_jump_power = 130f;		 
	    }
		  number_of_enemies = enemies.size();			 
		 
		  for(int z=0;z<enemy_bodies.length;z++)
		  { 
	    	enemies.get(z).SetGrounds(e_floor);
	      }	 
	} 
	
	public void SetTheDiamonds()
	{
		diamond_sb = new SpriteBatch();
		diamond_tx = new Texture(Gdx.files.internal("Game_Art/diamond_sprite.png"));
		
		
		for(int i=0;i<diamond_bodies.size();i++)
		{
			diamond_objects.add(new Diamonds(diamond_bodies.get(i).getPosition(),diamond_sb, diamond_tx, 60/100f, 60/100f));
		}
	
	}
	public void SetTheHearts()
	{		
		for(int i=0;i<heart_bodies.size();i++)
		{
			extra_energy_objects.add(new Extra_Energy(heart_bodies.get(i).getPosition(), camera) );			
		}			
	}	
	public void Load_CheckPoints_For_The_Player()
	{	
	    if(Game_Elements.to_continue)
	    {	  
	    	if(!Game_Elements.boss_reached)	    	
	    	b2dbody.setTransform(Game_Elements.last_point,0);    
	   	    	
	    		for(int i=0;i<extra_energy_objects.size();i++)
	    		{	    		
	    			if(b2dbody.getPosition().x > heart_bodies.get(i).getPosition().x)
	    				extra_energy_objects.get(i).heart_already_collected = true;   				
	    		}	    	
	    		Game_Elements.to_continue = false;	       		 
	    }	
	}
	
	public void SetTheCheckPoints()
	{
		checkpoint_positions = new ArrayList<Vector2>();
		sb_checkpoint_fail = new SpriteBatch();
		tx_checkpoint_fail = new Texture(Gdx.files.internal("Level_maps/checkpoint_fail.png"));	
		tx_checkpoint_pass = new Texture(Gdx.files.internal("Level_maps/go_right.png"));		
	}
	
	public void set_Shurikens()
	{			
		for(int i = 0;i<shuriken_bodies.length; i ++)
		{
			shurikens.add(new Shuriken());		
		}		
		for(int j=0;j<shurikens.size(); j++)
		{
			shurikens.get(j).animate_shuriken();
			shurikens.get(j).Animate_Shuriken_Rotate();		
		}
	}
	
	public void SetTheDynamicObjects()
	{
		dynamic_object_sb = new SpriteBatch();
		dynamic_object_tx = new Texture(Gdx.files.internal("Game_Art/dynamic_box.png"));
		
		
		for(int i=0;i<dynamic_object_bodies.size();i++)
		{
			dynamic_objects_objects.add(new Dynamic_objects(dynamic_object_bodies.get(i).getPosition(),
					dynamic_object_sb, dynamic_object_tx, 150/100f, 150/100f));
		}
	
	}

	private void Game_controller()
	{
	if(g_controller_type == "JoyPad")
	{
		//Checking out if both of the directional buttons are not pressed in one time...
 		if(!d_joy_pad.is_button_right_clicked && !d_joy_pad.is_button_left_clicked)
 			type_of_movement = "static_movement";		
 		// The right direction...
		if(enable_directional_buttons)
		{
		if((d_joy_pad.is_button_right_clicked || Gdx.input.isKeyPressed(Keys.RIGHT))&& !is_intro_mode)
	      { 		
	        	b2dbody.applyLinearImpulse(new Vector2(character_acceleration,0), b2dbody.getWorldCenter(), true); 	        	
	        	if(b2dbody.getLinearVelocity().x > character_speed_limit)
	        		b2dbody.setLinearVelocity(new Vector2(character_speed_limit, b2dbody.getLinearVelocity().y));
	        	movement="right"; 
	        	type_of_movement = "dynamic_movement";        		
	      }	
		}			
		//The left direction... 
		if(enable_directional_buttons)
		{
			 if((d_joy_pad.is_button_left_clicked|| Gdx.input.isKeyPressed(Keys.LEFT))
					 && !is_intro_mode && !is_automatic_walk_in_the_boss_zone)
			 {				
			 		b2dbody.applyLinearImpulse(new Vector2(-character_acceleration,0), b2dbody.getWorldCenter(), true);  
			 		if(b2dbody.getLinearVelocity().x<-character_speed_limit)
			 			b2dbody.setLinearVelocity(new Vector2(-character_speed_limit, b2dbody.getLinearVelocity().y));
			 		movement="left";
			 		type_of_movement = "dynamic_movement";			 	 
			 } 		 		
		}		
			        //Jump command...
	        		if(can_player_jump && enable_jump_button && (d_joy_pad.is_button_jump_pressed || Gdx.input.isKeyPressed(Keys.UP)) && !is_intro_mode && !is_on_the_edge)
	        		{  
	        			d_animation.stateTime_jumping_right = 0;
	        			d_animation.stateTime_jumping_left = 0;
	        			if(Math.abs(b2dbody.getLinearVelocity().x) > character_speed_limit)
	        				b2dbody.setLinearVelocity(0, b2dbody.getLinearVelocity().y);
	        		
	        			if(!different_platform_overlap)
	        				b2dbody.applyLinearImpulse(new Vector2(0,jump_force_character), b2dbody.getWorldCenter(), true); 	        			  
	        			else
	        				b2dbody.applyLinearImpulse(new Vector2(0,jump_force_character*1.625f), b2dbody.getWorldCenter(), true); 	        		
	        		}	      
	        		//edge jump
	        		if(is_on_the_edge && (Gdx.input.isKeyPressed(Keys.UP) || d_joy_pad.is_button_jump_pressed)        				
	        				&& b2dbody.getLinearVelocity().y >= -0.75f)
	        		{
	        			if(!d_contact_listener.Check_CharacterOnGround() && !on_sliding_platform)	        			
	        				b2dbody.applyLinearImpulse(new Vector2(0,jump_force_character*4.25f), b2dbody.getWorldCenter(), true);     
	        			if(!d_contact_listener.Check_CharacterOnGround() && on_sliding_platform)	     
	        				b2dbody.applyLinearImpulse(new Vector2(0,jump_force_character*2.125f), b2dbody.getWorldCenter(), true);     
	        			
	        			if(d_contact_listener.Check_CharacterOnGround())	        		       				
	        				b2dbody.applyLinearImpulse(new Vector2(0,jump_force_character), b2dbody.getWorldCenter(), true);	    
	        		}
	        			//////end of jump command....
	        		//Kick command...
	                if((d_joy_pad.is_button_kick_pressed || Gdx.input.isKeyPressed(Keys.SPACE ) || d_joy_pad.d_kick) && enable_kick_button && !is_intro_mode)
	        	    { 	
	                	_is_kick_pressed = true;
	                	
	                   //Right Kick...
	            	   if(movement.contains("right"))
	            	   {	            		  
	            		   	   count_of_kicks +=1;	            		   	 
	            			   movement = "right_kick";
	            			   d_animation.stateTime_right_kick = 0;
	            			   is_hitting = true;	            		 
	            	   }
	            	   
	            	   //Left Kick...
	            	   if(movement.contains("left"))
	            	   {	      
	            		   	   count_of_kicks +=1;	            		  
	            			   movement = "left_kick";
	            			   d_animation.stateTime_left_kick = 0;
	            			   is_hitting = true;	            		 
	            	   }			        		   
	        	    }
		}
	}	
	// Coming soon :))
	public void Shoot_Enemies(int Shoot_Index)
	{ 		
		//This will be added later...
	}
	private void PlayJumpSfx()
	{		
		if(!can_player_jump)
		{
			if(b2dbody.getLinearVelocity().y > 0.1f && !is_on_the_edge)
				sound_processor.PlaySoundFx('j');			
			if(b2dbody.getLinearVelocity().y <- 0.1f && is_on_the_edge)
				sound_processor.PlaySoundFx('r');
		}
		if(can_player_jump)			
			sound_processor.ResetFx();
	}
	
	public void CheckDamage()
	{
		enemy_force_limit +=1;
		if(enemy_force_limit>3)
			enemy_force_limit=0;
		
	   //Right Damage
 	   if(is_player_getting_damage)
 	   {	            		  
 		   if(damage_direction.equals("right_damage"))
 		      movement = "right_damage"; 		    	   
 	   //Left Damage 	   
 		   if(damage_direction.equals("left_damage"))
 		      movement = "left_damage"; 		   
 	   } 	   
	}
	
	public void Enemy_Behaviour()
	{		
		try
		{
			//Draw the enemy and set the Enemy AI
			for(int i=0;i<enemies.size();i++)
			{
				enemies.get(i).enemy_batch.setProjectionMatrix(camera.combined);	        	
				enemies.get(i).Enemy_AI(character_position, b2dbody, body_box,Gdx.graphics.getDeltaTime());	

				if(enemies.get(i).life==0 && !Game_Elements.to_continue)
				{    		
					sound_fx_enemy_lost.play(0.4f);
					enemies.get(i).life-=1;
				}
			}		
		}
			catch(Exception ex)
			{
				//System.out.println("Error!" + ex.toString());
			}	  
	}   
	
	public void Character_Kick_Controller()
	{	
        Rectangle character_kick_rectangle = new Rectangle(character_position.x+55/100f,character_position.y+50/100f,124/100f,30/100f);		
		
	    if(is_hitting)
	    {
	    	if(movement.equals("right_kick"))
	    	{		
	    		for(int i=0;i<enemies.size();i++)
	    		{
	    			enemies.get(i).Kick_Control(character_kick_rectangle, movement);
	      		}
	    	}	    	
	    	if(movement.equals("left_kick"))
	    	{		
				for(int i=0;i<enemies.size();i++)
				{
					enemies.get(i).Kick_Control(character_kick_rectangle, movement);
				}
			}
	    }	  
	}
	
	
	
   private void PlaySfxPlayerKickingEnemies()
   {
		int index_right_kick, index_right_punch,index_special_right,index_left_kick,index_left_punch,index_special_left;
	////////////////    RIGHT MOVES ALL SOUND CHECK \\\\\\\\\\\	
		//right kick sound check
		index_right_kick = 0;		
		for(int i=0;i<d_animation.animation_right_kick.getKeyFrames().length;i++)
		{
			if(d_animation.animation_right_kick.getKeyFrame(d_animation.stateTime_right_kick) 
					== d_animation.animation_right_kick.getKeyFrames()[i])		
				index_right_kick = i;			
		}
		if(index_right_kick == 2)
		{
			if(counter_right_kick == 0)
			id_kicking_enemy = sound_fx_kicking_enemy.play(0.9f);
			counter_right_kick++;
		}
		else 
			counter_right_kick = 0;
		
		//right punch sound check
		index_right_punch = 0;		
		for(int i=0;i<d_animation.animation_right_punch.getKeyFrames().length;i++)
		{
			if(d_animation.animation_right_punch.getKeyFrame(d_animation.stateTime_right_punch) 
					== d_animation.animation_right_punch.getKeyFrames()[i])		
				index_right_punch = i;			
		}
		if(index_right_punch == 2)
		{
			if(counter_right_punch == 0)
			id_kicking_enemy = sound_fx_kicking_enemy.play(0.9f);
			counter_right_punch++;
		}
		else 
			counter_right_punch = 0;
		
		//right special sound check
				index_special_right = 0;		
				for(int i=0;i<d_animation.alt_animation_right_kick.getKeyFrames().length;i++)
				{
					if(d_animation.alt_animation_right_kick.getKeyFrame(d_animation.alt_stateTime_right_kick) 
							== d_animation.alt_animation_right_kick.getKeyFrames()[i])		
						index_special_right = i;			
				}
				if(index_special_right == 3)
				{
					if(counter_special_right == 0)
					id_kicking_enemy = sound_fx_kicking_enemy.play(0.9f);
					counter_special_right++;
				}
				else 
					counter_special_right = 0;
		////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	    //////// LEFT MOVES ALL SOUND CHECK \\\\\\\\\\\
				//left kick sound check
				index_left_kick = 0;		
				for(int i=0;i<d_animation.animation_left_kick.getKeyFrames().length;i++)
				{
					if(d_animation.animation_left_kick.getKeyFrame(d_animation.stateTime_left_kick) 
							== d_animation.animation_left_kick.getKeyFrames()[i])		
						index_left_kick = i;			
				}
				if(index_left_kick == 2)
				{
					if(counter_left_kick == 0)
					id_kicking_enemy = sound_fx_kicking_enemy.play(0.9f);
					counter_left_kick++;
				}
				else 
					counter_left_kick = 0;
				
				//left punch sound check
				index_left_punch = 0;		
				for(int i=0;i<d_animation.animation_left_punch.getKeyFrames().length;i++)
				{
					if(d_animation.animation_left_punch.getKeyFrame(d_animation.stateTime_left_punch) 
							== d_animation.animation_left_punch.getKeyFrames()[i])		
						index_left_punch = i;			
				}
				if(index_left_punch == 2)
				{
					if(counter_left_punch == 0)
					id_kicking_enemy = sound_fx_kicking_enemy.play(0.9f);
					counter_left_punch++;
				}
				else 
					counter_left_punch = 0;
				
				//left special sound check
						index_special_left = 0;		
						for(int i=0;i<d_animation.alt_animation_left_kick.getKeyFrames().length;i++)
						{
							if(d_animation.alt_animation_left_kick.getKeyFrame(d_animation.alt_stateTime_left_kick) 
									== d_animation.alt_animation_left_kick.getKeyFrames()[i])		
								index_special_left = i;			
						}
						if(index_special_left == 3)
						{
							if(counter_special_left == 0)
							id_kicking_enemy = sound_fx_kicking_enemy.play(0.9f);
							counter_special_left++;
						}
						else 
							counter_special_left = 0;
	}
	
	public void Edge_Stand()
	{	
		float edge_stand_draw_size_multiplexer = 1.0285f;
		if(!is_player_getting_damage)
		{			
			if(movement.equals("right"))
			{
				
				d_animation.currentFrame_edge_stand_right = d_animation.animation_edge_stand_right.getKeyFrame(d_animation.stateTime_edge_stand_right, true);
				batch_character.draw(d_animation.currentFrame_edge_stand_right,character_position.x, character_position.y,
						player_size.x*edge_stand_draw_size_multiplexer,player_size.y*edge_stand_draw_size_multiplexer);
				d_animation.stateTime_edge_stand_right += Gdx.graphics.getDeltaTime();
				if(d_animation.animation_edge_stand_right.isAnimationFinished(d_animation.stateTime_edge_stand_right))
				{				
					d_animation.stateTime_edge_stand_right = 0;		
					counter_edge_stand = 0;				
				}
			}
			
			if(movement.equals("left"))
			{			
				d_animation.currentFrame_edge_stand_left = d_animation.animation_edge_stand_left.getKeyFrame(d_animation.stateTime_edge_stand_left, true);
				batch_character.draw(d_animation.currentFrame_edge_stand_left,character_position.x, character_position.y,
						player_size.x*edge_stand_draw_size_multiplexer,player_size.y*edge_stand_draw_size_multiplexer);
				d_animation.stateTime_edge_stand_left += Gdx.graphics.getDeltaTime();
				if(d_animation.animation_edge_stand_left.isAnimationFinished(d_animation.stateTime_edge_stand_left))
				{
					d_animation.stateTime_edge_stand_left = 0;			
					counter_edge_stand = 0;			
				}
			}			
		}		
	}
	
	public void Roof_Walk()
	{    	
    	//roof walk
    	//walking right on the roof    
    		if(movement == "right"&& !is_player_getting_damage)
    		{
    			if(!is_intro_mode)
    				d_animation.currentFrame_walk_right = d_animation.animation_walk_right.getKeyFrame(d_animation.stateTime_walk_right,false);
    			if(is_intro_mode)
    				d_animation.currentFrame_walk_right = d_animation.animation_walk_right.getKeyFrames()[0];

    	
    			batch_character.draw(d_animation.currentFrame_walk_right, character_position.x, character_position.y,player_size.x,player_size.y);   
    			
    			d_animation.stateTime_walk_right +=Gdx.graphics.getDeltaTime()/2;				
    			if(d_animation.animation_walk_right.isAnimationFinished(d_animation.stateTime_walk_right) )
    			{						
    				d_animation.stateTime_walk_right = 0;				
    			}		
    			if(is_intro_mode)
    				d_animation.currentFrame_walk_right = d_animation.animation_walk_right.getKeyFrames()[0];    			
    		}    		
    		//walking left on the roof	    		
    		if(movement == "left"  && !is_player_getting_damage)
    		{
    			if(!is_intro_mode)
    				d_animation.currentFrame_walk_left = d_animation.animation_walk_left.getKeyFrame(d_animation.stateTime_walk_left,false);
    			if(is_intro_mode)
    				d_animation.currentFrame_walk_left = d_animation.animation_walk_left.getKeyFrames()[0];
    	
    		
    			batch_character.draw(d_animation.currentFrame_walk_left, character_position.x, character_position.y,player_size.x,player_size.y);	
    	
    			
    			d_animation.stateTime_walk_left +=Gdx.graphics.getDeltaTime()/2;				
    			if(d_animation.animation_walk_left.isAnimationFinished(d_animation.stateTime_walk_left) )
    			{						
    				d_animation.stateTime_walk_left = 0;				
    			}		
    			if(is_intro_mode)
    				d_animation.currentFrame_walk_left = d_animation.animation_walk_left.getKeyFrames()[0];    			
    		}    		
    
	}	
	public void my_Dispose()
	{	
	    level_music.stop();
	    level_music.dispose();
		boss_zone_music.stop();
		boss_zone_music.dispose();
		boss_music.stop();
		boss_music.dispose();
		win_music.stop();
		win_music.dispose();
		
		diamond_borders.clear();
		diamond_objects.clear();	
		heart_borders.clear();
		extra_energy_objects.clear();
		dynamic_object_borders.clear();
		dynamic_objects_objects.clear();				
		
		if(!enemies_already_disposed)
		{
			for(int i=0;i<enemies.size();i++)
			{
				enemies.get(i).shuriken_movement ="";
				enemies.get(i).can_throw_shuriken = false;
				enemies.get(i).enemy_animation_spread_sheet.dispose();
				enemies.get(i).enemy_animation_spread_sheet_lost.dispose();
				enemies.get(i).sound_fx_enemy_lost.dispose();	
//				enemies.get(i).boss_talk_baloon_sprite.dispose();
				enemies.get(i).boss_talk_baloon_texture.dispose();
//				enemies.get(i).enemy_batch.dispose();						
				for(int z=0;z<enemies.get(i).enemy_ui.tx_energies.length;z++)
				{
					enemies.get(i).enemy_ui.tx_energies[z].dispose();							
				}						
			}
			enemies_already_disposed = true;
		}
		
 	    e_floor.clear();	
	
		for(int s=0;s<shurikens.size();s++)
		{
		shurikens.get(s).sb_shuriken.dispose();
		shurikens.get(s).shuriken_animation_spread_sheet.dispose();
		shuriken_bodies[s].setTransform(1000,1000, 0);
		}
		
		for(int s=0;s<extra_energy_objects.size();s++)
		{
			extra_energy_objects.get(s).tx_heart.dispose();
			extra_energy_objects.get(s).sb_heart.dispose();
		}
		shurikens.clear();		
		sound_fx_character_lost.dispose();
		sound_fx_diamond.dispose();
		sound_fx_enemy_lost.dispose();
		sound_fx_player_being_hit.dispose();
		sound_fx_kick_wind.dispose();
		sound_fx_kicking_enemy.dispose();
		tiled_map.dispose();	
	    
		d_animation.character_animation_spread_sheet.dispose();
		
		d_joy_pad.tx_jump.dispose();			
		d_joy_pad.tx_kick.dispose();		
		d_joy_pad.tx_right.dispose();		
		d_joy_pad.tx_left.dispose();			
		for(int t=0;t<ui.tx_energies.length;t++)
		{
			ui.tx_energies[t].dispose();
		}		
		for(int dpp=0;dpp<dpp_objects.size();dpp++)
		{
			dpp_objects.get(dpp).tx_dp.dispose();
		}
		tx_checkpoint_fail.dispose();
		tx_checkpoint_pass.dispose();
		ui.tx_diamond_img_sheet.dispose();
		ui.bitmap_font.dispose();
		ui.tx_character_icon.dispose();
		ui.tx_boss_icon.dispose();
		ui.tx_life_icon.dispose();
		for(int i=0;i<ip_mx.getProcessors().size;i++)
		{
			ip_mx.removeProcessor(i);
		}
		extra_gui_element.DisposeEverything();
		this.dispose();
	}
	
	public void Player_Losing()
	{	 
		b2dbody.setLinearVelocity(new Vector2(0,b2dbody.getLinearVelocity().y));
				lose_sound_time_counter += 1;
				if(lose_sound_time_counter == 1)
				{
					sound_fx_enemy_lost.play(0.8f);		
					   try{
							Game_Elements.SetLivesLeft();											
							}
							catch(Exception ex)
							{
							System.out.println("hata" + ex.toString());
							}
				}
				if(lose_sound_time_counter>90)
				{
					sound_fx_enemy_lost.stop();					
				}						
				//Lose Right				
				if(movement.contains("left"))
				{
					enable_kick_button = false;				
					enable_directional_buttons = false;					
					d_animation.currentFrame_losing_right = d_animation.animation_losing_right.getKeyFrame(d_animation.stateTime_losing_right+0.12f,false);
			
					batch_character.draw(d_animation.currentFrame_losing_right, character_position.x, character_position.y,player_size.x,player_size.y);	
		
					d_animation.stateTime_losing_right +=Gdx.graphics.getDeltaTime();				
			
					
					if(d_animation.animation_losing_right.isAnimationFinished(d_animation.stateTime_losing_right))
					{				
						d_animation.currentFrame_losing_right = d_animation.animation_losing_right.getKeyFrames()[3];
						is_player_getting_damage = false;											
						enable_directional_buttons = false;					
						enable_kick_button = false;					
						enable_jump_button = false;								
										
						Game_Elements.SetLevelData(Game_Elements.GetLevelData());			
						my_Dispose();		
						timer_shift_screens +=1;
						if(timer_shift_screens > 150)
						{							
							timer_shift_screens =0;						
							game.setScreen(game.lost_menu);	
						}
												
					}
				}
				//end of lose right...
				
				
				//Lose left
				if(movement.contains("right"))
				{
					enable_kick_button = false;				
					enable_directional_buttons = false;					
					d_animation.currentFrame_losing_left = d_animation.animation_losing_left.getKeyFrame(d_animation.stateTime_losing_left+0.12f,false);
			
					batch_character.draw(d_animation.currentFrame_losing_left, character_position.x,  character_position.y, player_size.x, player_size.y);	
		
					d_animation.stateTime_losing_left +=Gdx.graphics.getDeltaTime();				
				
			    
					if(d_animation.animation_losing_left.isAnimationFinished(d_animation.stateTime_losing_left))
					{				
						d_animation.currentFrame_losing_left = d_animation.animation_losing_left.getKeyFrames()[3];
						is_player_getting_damage = false;											
						enable_directional_buttons = false;						
						enable_kick_button = false;						
						enable_jump_button = false;																
						Game_Elements.SetLevelData(Game_Elements.GetLevelData());	
						my_Dispose();		
						timer_shift_screens +=1;
						if(timer_shift_screens > 150)
						{
						
							timer_shift_screens =0;
							game.setScreen(game.lost_menu);	
						}
								
					}			
				}
					// end of lose left...		
	}
	
	public  void Check_Dynamic_Objects_Player_Collusion()
	{	
		for(int o=0;o<dynamic_object_borders.size();o++)
		{		
			 dob = new Rectangle(dynamic_object_borders.get(o).x-27/100f, dynamic_object_borders.get(o).y-20/100f,60/100f,60/100f);
	     	

			 if(char_dob.overlaps(dob))
	     	 {     		
	     	    can_player_jump_on_dob = true;	     	    
	     	     	    
	     	 }        		
	     }		 	
	}
	
	public void Check_Player_Trap_Collision()
	{	 
		for(int i=0;i<traps.size();i++)
		{			
			
			if(body_box.overlaps(traps.get(i)) && is_player_alive)
			{
				//Response from the traps.			
				if(movement.equals("right"))
				{					
					enable_directional_buttons = false;					
					enable_jump_button = false;					
					enable_kick_button = false;				
					b2dbody.applyLinearImpulse(new Vector2(-42f,60f), b2dbody.getWorldCenter(), true);
					is_player_getting_damage = true;					
					damage_direction = "right_damage";
					UI.player_energy -= 1000f;
				}
				if(movement.equals("left"))
				{					
					enable_directional_buttons = false;					
					enable_jump_button = false;				
					enable_kick_button = false;				
					b2dbody.applyLinearImpulse(new Vector2(42f,60f), b2dbody.getWorldCenter(), true);
					is_player_getting_damage = true;					
					damage_direction = "left_damage";
					UI.player_energy -=1000f;
				}							
			}			
		}
	}
	
	public void Kick_The_Boxes()
	{
		Rectangle cr_brd4_kick = new Rectangle(b2dbody.getPosition().x-60/100f, b2dbody.getPosition().y-56/100f, 120/100f, 80/100f);
		Rectangle dyb_brd4_kick = null;		
		
		for(int i=0;i<dynamic_object_borders.size();i++)
		{
			 dyb_brd4_kick = new Rectangle(dynamic_object_borders.get(i).x-35/100f, dynamic_object_borders.get(i).y-25/100f, 75/100f, 60/100f);

	    	if(cr_brd4_kick.overlaps(dyb_brd4_kick) && is_hitting)
			 {
	    		//count_of_kicks = 0;
				 if(character_position.y < dyb_brd4_kick.y)
				 {
					 if(movement =="right_kick" && dynamic_object_bodies.get(i).getPosition().x>b2dbody.getPosition().x)				 
					 {
						 dynamic_object_bodies.get(i).applyLinearImpulse(new Vector2(6f,8.5f), new Vector2(dynamic_object_bodies.get(i).getWorldCenter().x,dynamic_object_bodies.get(i).getWorldCenter().y), true);
						
					 }	
					 if(movement =="left_kick" && dynamic_object_bodies.get(i).getPosition().x<b2dbody.getPosition().x)					 	 
					 {
						 dynamic_object_bodies.get(i).applyLinearImpulse(new Vector2(-6f,8.5f), new Vector2(dynamic_object_bodies.get(i).getWorldCenter().x,dynamic_object_bodies.get(i).getWorldCenter().y), true);
					 }	
					 if(Math.abs(dynamic_object_bodies.get(i).getLinearVelocity().x)>8f)
						 dynamic_object_bodies.get(i).setLinearVelocity(0, 0);
				 }					 
			 }				 
		}
	}
	
	public void Check_Jump_On_Boxes()
	{		
		for(int i =0; i< dynamic_object_borders.size(); i++)
		{
			
			dob = new Rectangle(dynamic_object_borders.get(i).x-29/100f, dynamic_object_borders.get(i).y-23/100f, 60/100f, 60/100f);
			if(body_box.overlaps(dob))
			can_player_jump_on_dob = true;
		}     
	}
	
    public void Diamond_Behaviour()
    {
    	for(int s=0;s<diamond_borders.size();s++)
  	    {
    		diamond_borders.get(s).set(diamond_bodies.get(s).getPosition().x+6.8f/100f,diamond_bodies.get(s).getPosition().y,24/100f,40/100f);
           	if(diamond_borders.get(s).overlaps(body_box))
  	    	{
  	    		//System.out.println("Diamond overlap");  	    		
  	    		sound_fx_diamond.stop();  	    		  	
  	    		diamond_bodies.get(s).setTransform(new Vector2(diamond_bodies.get(s).getPosition().x,diamond_bodies.get(s).getPosition().y-1000), 0);
  	    		//add into the number of collected diamonds...  	    		
  	    		diamond_borders.get(s).x = diamond_bodies.get(s).getPosition().x;
  	    		diamond_borders.get(s).y = diamond_bodies.get(s).getPosition().y;
  	    		counter_diamond_sound_fx +=1;
  	    		if(counter_diamond_sound_fx ==1)
  	    		{  	    			  
  	    			Game_Elements.total_diamonds +=1;
  	    			in_level_collected_diamonds = Game_Elements.total_diamonds;
  	    			Bonus_Life();
  	    			sound_fx_diamond.play(0.525f);
  	    			counter_diamond_sound_fx = 0;
  	    		}
  	    	}
  	    	
  	    }  	 
  	    //Draw the diamonds...
  		for(int d=0;d<diamond_objects.size();d++)
  		{
  			diamond_objects.get(d).diamond_pos = diamond_bodies.get(d).getPosition();
  			diamond_objects.get(d).Draw_Diamond(camera);
  		}
    }
    
    public void Place_Hearts()
    {    	
    	for(int i=0; i<extra_energy_objects.size();i++)
    	{  	
    		if(!extra_energy_objects.get(i).heart_already_collected)
    		{
    			extra_energy_objects.get(i).Draw_Heart();
    			if(heart_borders.get(i).overlaps(body_box))
    			{
    				extra_energy_objects.get(i).heart_taken = true;    				  				
    			}
    		}
    	}
    	for(int j=0; j<extra_energy_objects.size();j++)
    	{
    		if(extra_energy_objects.get(j).heart_taken)
    		{
    			sound_fx_heart.play(0.6f);
    			if(UI.player_energy<99)
    				UI.player_energy =99f;
    			heart_bodies.get(j).setTransform(new Vector2(10000,10000), 0);
    			heart_borders.get(j).set(new Rectangle(heart_bodies.get(j).getPosition().x,heart_bodies.get(j).getPosition().y,64/100f,64/100f));    			
    			extra_energy_objects.get(j).heart_taken = false;
    		}    		
    		if(extra_energy_objects.get(j).heart_already_collected)
    		{
    			heart_bodies.get(j).setTransform(new Vector2(10000,10000), 0);
    			heart_borders.get(j).set(new Rectangle(heart_bodies.get(j).getPosition().x,heart_bodies.get(j).getPosition().y,64/100f,64/100f));    			
    		}
    	}    	
    }
    
    public void Dynamic_Objects_Behaviour()
    {
    	for(int dyob=0;dyob<dynamic_objects_objects.size();dyob++)
  		{
  			dynamic_objects_objects.get(dyob).dynamic_object_position = dynamic_object_bodies.get(dyob).getPosition();
  			dynamic_objects_objects.get(dyob).Draw_Dynamic_Object(camera);
  			dynamic_object_borders.get(dyob).setPosition(dynamic_object_bodies.get(dyob).getPosition().x, dynamic_object_bodies.get(dyob).getPosition().y);
  		}
    }
    
    public void Check_End_Of_Level()
	{      	
    		if(character_position.x>exit_position.x && enemies.get(7).is_enemy_dead)
    		{
    			if(did_finish_the_level)
    			end_of_level = true;    			
    		}    
	}
    public void Check_Drop_Ground()
    {
    	
    		if(b2dbody.getPosition().y<0)
    		{
    			enable_directional_buttons = false;    		
    			enable_kick_button = false;    		
    			enable_jump_button = false;    			
    		    is_player_alive = false;    		   
    			UI.player_energy -= 1000000f;
    			if(b2dbody.getPosition().y == -1)
    				Game_Elements.SetLivesLeft();
    			
    			//The code below is for stopping the camera to follow the character after lost the game. 
    			//Stop the character floating so the camera will stop with the character too
    			if(b2dbody.getPosition().y<-200/100f)
    			b2dbody.setLinearDamping(1000f);
    		}   	
    		
    }
    private void Moves_Regulator()
    {
    	 //Moves regulator 
       	for(int i=0;i<enemies.size();i++)
       	{        	
       		//Moves regulator for both enemies and character...
       		if(Math.abs(enemies.get(i).enemy_body.getPosition().x-b2dbody.getPosition().x)<0.6)
       		{         			
       		//Stop the enemy if too close       			
       			enemies.get(i).enemy_body.setLinearVelocity(0,enemies.get(i).enemy_body.getLinearVelocity().y);
       		//character kick animation regulator...
       			if(enemies.get(i).enemy_body.getPosition().x < b2dbody.getPosition().x && d_contact_listener.is_character_on_the_ground)
       			{
       				if(movement.contains("kick"))
       				{
       						b2dbody.setLinearVelocity(0f,b2dbody.getLinearVelocity().y);       				     
       					enemies.get(i).enemy_body.applyLinearImpulse(new Vector2(-60f, 0), enemies.get(i).enemy_body.getWorldCenter(),true);
       				}       				
       			}
       			if(enemies.get(i).enemy_body.getPosition().x > b2dbody.getPosition().x && d_contact_listener.is_character_on_the_ground)
       			{
       				if(movement.contains("kick"))
       				{
       					b2dbody.setLinearVelocity(0f,b2dbody.getLinearVelocity().y);       					
       					  enemies.get(i).enemy_body.applyLinearImpulse(new Vector2(60f, 0), enemies.get(i).enemy_body.getWorldCenter(),true);
       				}
       			} 
       			if( movement.contains("damage") )
       				b2dbody.setLinearVelocity(0f, b2dbody.getLinearVelocity().y);      			
       		}
       		if(Math.abs(enemies.get(i).enemy_body.getPosition().x-b2dbody.getPosition().x)<0.8  && !enemies.get(i).is_enemy_dead 
       				&& d_contact_listener.is_character_on_the_ground && enemies.get(i).e_movement.contains("kicking"))
       		{
       			if(enemies.get(i).enemy_body.getPosition().x <  b2dbody.getPosition().x)
       			{
       				if(movement.contains("left"))
       					b2dbody.setLinearVelocity(new Vector2(0f,b2dbody.getLinearVelocity().y));
       				if(movement.contains("right"))
       					b2dbody.setLinearVelocity(new Vector2(b2dbody.getLinearVelocity().x,b2dbody.getLinearVelocity().y));
       			}
       			
       			if(enemies.get(i).enemy_body.getPosition().x >  b2dbody.getPosition().x)
       			{
       				if(movement.contains("right"))
       					b2dbody.setLinearVelocity(new Vector2(0f,b2dbody.getLinearVelocity().y));
       				if(movement.contains("left"))
       					b2dbody.setLinearVelocity(new Vector2(b2dbody.getLinearVelocity().x,b2dbody.getLinearVelocity().y));
       			}
       		}
       		
       		if(Math.abs(enemies.get(i).enemy_body.getPosition().x-b2dbody.getPosition().x)<0.48f && enemies.get(i).e_movement.contains("kick"))
       		{
       			if(movement.contains("right"))       		   			
       			b2dbody.applyLinearImpulse(130f, 0F,b2dbody.getWorldCenter().x, b2dbody.getWorldCenter().y, true);
       		
       			if(movement.contains("left"))       			     			
       			b2dbody.applyLinearImpulse(-160f, 0F,b2dbody.getWorldCenter().x, b2dbody.getWorldCenter().y, true);       			
       		}       		
       	}     
    }
    private void Dynamic_Box_Contact_Controller()
    {
    	if(d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob)
       	{     		
       		can_player_jump = true;        		
       	}        	
    	if(!d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob)
    	{    		
    		can_player_jump = true; 
    	}
    	if(!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob)
    	{    		
    		can_player_jump = false; 
    	}    	
    	if(d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob)
    	{    		
    		can_player_jump = true;   		
    	}    
    	if(!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob && on_sliding_platform)
    	{    		
    		can_player_jump = true;   		
    	}    
    }   
    
	public void Draw_player_Talk_Baloon()
	{
		player_talk_baloon_sprite.enableBlending();
		player_talk_baloon_sprite.begin();
		player_talk_baloon_sprite.setProjectionMatrix(camera.combined);
		player_talk_baloon_sprite.draw(player_talk_baloon_texture, character_position.x-50/100f, character_position.y+175/100f,180/100f,128/100f);	
		player_talk_baloon_sprite.end();
	}
    
    public void Boss_Behaviour(float Delta_time_for_intro)
    {
    	boss_zone_rectangle.set(boss_zone_position.x-84/100f, boss_zone_position.y,1024/100f,224/100f);   
    
    	//Check if you are in the boss_zone...	 
		 	if( body_box.overlaps(boss_zone_rectangle) )
		 	{ 	
		 		Game_Elements.boss_reached = true;
		 		if( !Game_Elements.game_paused)
		 		{		 			
		 			enable_jump_button = false;		 		
		 			is_automatic_walk_in_the_boss_zone = true;
		 		}
		 		if(!Game_Elements.game_paused)
		 		{
		 			if(Math.abs(character_position.x - enemies.get(7).enemy_position.x)<3f)
		 			{
		 				is_intro_mode = true;		 		
		 				intro_wait_time += Delta_time_for_intro;		 				
		 			}
		 		}		
		 		//change the waiting time for the baloons...
		 			if( intro_wait_time < 3.25f )
		 			{	
		 				enemies.get(7).Draw_boss_talk_baloon();
		 				//bosszonemusic.play();		 				
		 			}			 		
		 			if( intro_wait_time > 3.25f && intro_wait_time < 6f )
		 			{			 			
		 				Draw_player_Talk_Baloon();		
		 				enemies.get(7).is_boss_talking = true;
		 			}		 		
		 		if( intro_wait_time > 6f && !player_wins )
		 		{		 		
		 			enable_jump_button = true;		 	
		 			enemies.get(7).is_boss_talking = false;		 		
		 			is_intro_mode = false;		 		
		 			is_automatic_walk_in_the_boss_zone = false;
		 			is_fighting_with_the_boss = true;
		 		}
		 	}
		 	
			// walk automatically in the boss zone...	
		 	
		 	if( !Game_Elements.game_paused && body_box.overlaps(boss_zone_rectangle) 
		 			&& (character_position.x < enemies.get(7).enemy_position.x-3f) &&  is_automatic_walk_in_the_boss_zone)
		 	{		 		
		 		if(b2dbody.getLinearVelocity().x < character_speed_limit)
    			{
    				b2dbody.applyLinearImpulse(new Vector2(character_acceleration,0), b2dbody.getWorldCenter(), true); 
    				movement="right"; 
    				type_of_movement = "dynamic_movement";
    			}
		 	}	
		 	if(enemies.get(7).is_enemy_dead)
		 	{
		 		player_wins = true;
		 		extra_gui_element.DrawStageClearBackground();
		 		ui.label_stage_clear.setVisible(true);
		 		if(time_of_playing_win_animation == 10)
		 		{
		 			try {win_music.play();} 
					catch(Exception ex) 
					{ 
					win_music = null; 
					win_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_win.ogg"));
					win_music.play();
					//System.out.println("Gumlettinovski win_music");
					}			
		 		}
		 		//UI.label_messageBox.setText(String.valueOf(time_of_playing_win_animation));		 		
		 	}		 	
		 }
    
    private void Throw_Shurikens(int ninja_index, int shuriken_index)
    {    	
    	float min_distance_for_shurikens = 1.5f;
    	float max_distance_for_shurikens = 6;
    	float shuriken_speed = 24f;
    	
    	shuriken_timer += 1;
    	int shuriken_throwing_interval =  100;
    	if(shuriken_timer > 10001)
    	{
    		shuriken_timer = 0;
    	}
    	
    	//Enemies throw the shurikens to the right side...
		if(enemies.get(ninja_index).enemy_body.getPosition().x < b2dbody.getPosition().x && !enemies.get(ninja_index).is_enemy_dead)
		{   				
			if( Math.abs(enemies.get(ninja_index).enemy_body.getPosition().x - b2dbody.getPosition().x) > min_distance_for_shurikens 
					&& Math.abs(enemies.get(ninja_index).enemy_body.getPosition().x - b2dbody.getPosition().x) < max_distance_for_shurikens)
			{
				if(shuriken_timer % shuriken_throwing_interval == 0)	
				{
					enemies.get(ninja_index).shuriken_movement ="shuriken_right";
					shurikens.get(shuriken_index).shuriken_direction = "shuriken_right";
					shuriken_bodies[shuriken_index].setLinearVelocity(new Vector2(0,0));
					shuriken_bodies[shuriken_index].setTransform(enemies.get(ninja_index).enemy_body.getPosition().x-80/100f, enemies.get(ninja_index).enemy_body.getPosition().y-30/100f, 0);
				if(enemies.get(ninja_index).can_throw_shuriken)
					shuriken_bodies[shuriken_index].applyLinearImpulse(new Vector2(shuriken_speed, 0), shuriken_bodies[shuriken_index].getWorldCenter(), true);
				}
			}
		}
		//Enemies throw the shurikens to the left side...
		if(enemies.get(ninja_index).enemy_body.getPosition().x > b2dbody.getPosition().x &&  !enemies.get(ninja_index).is_enemy_dead)
		{    				
			if(Math.abs(enemies.get(ninja_index).enemy_body.getPosition().x - b2dbody.getPosition().x) > min_distance_for_shurikens 
					&& Math.abs(enemies.get(ninja_index).enemy_body.getPosition().x - b2dbody.getPosition().x) < max_distance_for_shurikens) 
					
			{			
				if(shuriken_timer % shuriken_throwing_interval== 0)	
				{
					enemies.get(ninja_index).shuriken_movement ="shuriken_left";
					shurikens.get(shuriken_index).shuriken_direction = "shuriken_left";
					shuriken_bodies[shuriken_index].setLinearVelocity(new Vector2(0,0));
					shuriken_bodies[shuriken_index].setTransform(enemies.get(ninja_index).enemy_body.getPosition().x-60/100f, enemies.get(ninja_index).enemy_body.getPosition().y-30/100f, 0);
					if(enemies.get(ninja_index).can_throw_shuriken)
					shuriken_bodies[shuriken_index].applyLinearImpulse(new Vector2(-1*shuriken_speed, 0), shuriken_bodies[shuriken_index].getWorldCenter(), true);
				}
			}
		}		
		if(Math.abs(shuriken_bodies[shuriken_index].getLinearVelocity().x)<0.1f)
		{
			shuriken_bodies[shuriken_index].setTransform(new Vector2(10000,10000),0);
		}
    }	
    public void Draw_Shurikens()
    {  	
    	if(!Game_Elements.game_paused)
    	{
    		//Draw the shurikens...
    		for(int i=0;i<shurikens.size();i++)
    		{    			
    			shurikens.get(i).shuriken_position = shuriken_bodies[i].getPosition();
    			shurikens.get(i).Draw_Shuriken_Animation(camera);   	   		
    		}     	
    		if(is_player_alive)
    		{
    				Throw_Shurikens(1,0);  			//black ninja.
    				Throw_Shurikens(2,1);				//green ninja.
    				Throw_Shurikens(5,2);				// red 	ninja.   		
    		}
    	}
    }
    
    private void CheckShurikenPlayerContact()
    {    	
    		for(int i=0;i<shurikens.size();i++)
    		{
    			if(shurikens.get(i).border_of_shuriken.overlaps(body_box))
    			{    			
    				//Damage Settings...     		 
    				if(shurikens.get(i).shuriken_direction.equals("shuriken_right"))
    					damage_direction = "right_damage";
    				if(shurikens.get(i).shuriken_direction.equals("shuriken_left"))
    					damage_direction = "left_damage";      		 
    					    
    				is_player_getting_damage = true;
    		    
    				UI.player_energy -= 0.5f;    		
    				GameController.VibrateJoypad();
    			}    	
    		}    
    }    
  
    private void DrawCheckPoints()
    {
     	sb_checkpoint_fail .enableBlending();
    	sb_checkpoint_fail .begin(); 
    	sb_checkpoint_fail .setProjectionMatrix(camera.combined);
    	if(!Game_Elements.check_0_passed)        		
    	sb_checkpoint_fail .draw(tx_checkpoint_fail, checkpoint_bodies.get(0).getPosition().x-64/100f, 
    			checkpoint_bodies.get(0).getPosition().y/2.5f+10/100f,148/100f,148/100f);
    	else 
    		sb_checkpoint_fail .draw(tx_checkpoint_pass, check_passes[0].x-64/100f, 
    				check_passes[0].y/2.5f+10/100f,148/100f,148/100f);
    	if(!Game_Elements.check_1_passed)        		
    	sb_checkpoint_fail .draw(tx_checkpoint_fail, checkpoint_bodies.get(1).getPosition().x-64/100f, 
    			checkpoint_bodies.get(1).getPosition().y/2.5f+10/100f,148/100f,148/100f);
    	else 
    		sb_checkpoint_fail .draw(tx_checkpoint_pass, check_passes[1].x-64/100f, 
    				check_passes[1].y/2.5f+10/100f,148/100f,148/100f);
    	if(!Game_Elements.check_2_passed)        		
    	sb_checkpoint_fail .draw(tx_checkpoint_fail, checkpoint_bodies.get(2).getPosition().x-64/100f, 
    			checkpoint_bodies.get(2).getPosition().y/2.5f+10/100f,148/100f,148/100f);     
    	else 
    		sb_checkpoint_fail .draw(tx_checkpoint_pass, check_passes[2].x-64/100f, 
    				check_passes[2].y/2.5f+10/100f,148/100f,148/100f); 
    	sb_checkpoint_fail .end();	
    }
    private void Arrange_CheckPoints()
    {   
    	DrawCheckPoints();
//   	 	System.out.println( 
//    			"Bir : "  + checkpoint_bodies.get(0).getUserData() + " pos " + checkpoint_bodies.get(0).getPosition().x + 
//    			" iki :" + checkpoint_bodies.get(1).getUserData() + " pos " + checkpoint_bodies.get(1).getPosition().x + 
//    			" Uc :" + checkpoint_bodies.get(2).getUserData() + " pos " + checkpoint_bodies.get(2).getPosition().x );  
   	 	
   	 	//checkpoint 0
   	 	int n1_beaten_enemy = 0;
   	 	for (Enemy _enemy : enemies) 
   	 	{
   	 		
   	 		if(_enemy.is_enemy_dead && _enemy.enemy_body.getPosition().x < checkpoint_bodies.get(0).getPosition().x)
   	 			n1_beaten_enemy++;   	 	
		} 
   		if(n1_beaten_enemy==2)
 			Game_Elements.check_0_passed = true;
	 		if(Game_Elements.check_0_passed) 
	 		{
	 			Game_Elements.last_point = new Vector2(check_passes[0].x, check_passes[0].y);
	 			checkpoint_bodies.get(0).setTransform(new Vector2(checkpoint_bodies.get(0).getPosition().x,-1000000), 0);
	 		}
	 		
 	 	//checkpoint 1
   	 	int n2_beaten_enemy = 0;
   	 	for (Enemy _enemy : enemies) 
   	 	{
   	 		
   	 		if(_enemy.is_enemy_dead && _enemy.enemy_body.getPosition().x > checkpoint_bodies.get(0).getPosition().x
   	 				&& _enemy.enemy_body.getPosition().x < checkpoint_bodies.get(1).getPosition().x)   	 		
   	 			n2_beaten_enemy++;     	 	
		} 
   		if(n2_beaten_enemy==2)
 			Game_Elements.check_1_passed = true;
	 		if(Game_Elements.check_1_passed)
	 		{
	 			Game_Elements.last_point = new Vector2(check_passes[1].x, check_passes[1].y);
	 			checkpoint_bodies.get(1).setTransform(new Vector2(checkpoint_bodies.get(1).getPosition().x,-1000000), 0);	 	
	 		}
	 		
	 		//checkpoint 2
		 	int n3_beaten_enemy = 0;
	   	 	for (Enemy _enemy : enemies) 
	   	 	{
	   	 		
	   	 		if(_enemy.is_enemy_dead && _enemy.enemy_body.getPosition().x > checkpoint_bodies.get(0).getPosition().x
	   	 				&& _enemy.enemy_body.getPosition().x > checkpoint_bodies.get(1).getPosition().x && 
	   	 			_enemy.enemy_body.getPosition().x < checkpoint_bodies.get(2).getPosition().x)   	 		
	   	 			n3_beaten_enemy++;   
	   	 			 	
			} 
	   		if(n3_beaten_enemy==2)
	 			Game_Elements.check_2_passed = true;
		 		if(Game_Elements.check_2_passed)		 	
		 		{
		 			Game_Elements.last_point = new Vector2(check_passes[2].x, check_passes[2].y);
		 			checkpoint_bodies.get(2).setTransform(new Vector2(checkpoint_bodies.get(2).getPosition().x,-1000000), 0);		 
		 		}
		 		
	 		//System.out.println("n1 " + n1_beaten_enemy + " n2 " + n2_beaten_enemy + " n3 " + n3_beaten_enemy );
   	
   	BossZone();
    }
    private void BossZone()
    {
    	if(Game_Elements.boss_reached && !is_fighting_with_the_boss)
 		{
 			level_music.stop();
 			try {
 			boss_zone_music.play();
 			}
 			catch(Exception ex)
 			{
 				boss_zone_music = null;
 				boss_zone_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_boss_zone.ogg"));
 				boss_zone_music.play();
 				//System.out.println("gumletmece boss zone");
 			}
 			Game_Elements.SetLastPoint(boss_zone_position);
 		}
 		if(is_fighting_with_the_boss)
 		{
 			boss_zone_music.stop();
 			try 
 			{
 				boss_music.play();
 			}
 			catch(Exception ex)
 			{     			
 				boss_music = null;
 				boss_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background_music/bg_music_boss.ogg"));
 				boss_music.play();
 				//System.out.println("Gumlettin boss_zone");
 			}
 		}    		
    }
    private void ReturnBack()
    {
    	if(!Game_Elements.boss_reached)
    	{
    		for (Enemy _e : enemies) {
				if(_e.enemy_body.getPosition().x<Game_Elements.last_point.x)
				{
					_e.sound_fx_enemy_lost.dispose();
					_e.life =-1000;
				}
			}
    		for (Body _diamond : diamond_bodies)
    		{
    			if(_diamond.getPosition().x<Game_Elements.last_point.x)
				_diamond.setTransform(new Vector2(_diamond.getPosition().x, -1000000),  0);
			}
    		b2dbody.setTransform(new Vector2(Game_Elements.last_point.x,Game_Elements.last_point.y), 0);
    	}
    }    
    private void Arrange_Dynamic_Platforms()
    {
    	float sliding_speed=  40f/100f;   
    	
    	for(int d=0;d<dynamic_platformBodies.size();d++)
    	{
    		dpp_objects.get(d).Draw_Dp(camera);
    	}    		

    	for(int d=0; d<tiled_map.getLayers().get("DynamicPlatforms").getObjects().getCount(); d++)
		 {
			dynamic_platform_indexx[d] = (tiled_map.getLayers().get("DynamicPlatforms").getObjects().getIndex("dp_0" + d));		 
		 }      
    	//MoVe the dynamic platforms please :)
    	try
		{
			for(int j=0;j<border_of_dynamic_platforms.size();j++)
			{					
		    	if(border_of_dynamic_platforms.get(j).overlaps(dpp_starts.get(j)))
		    	{
		    		platform_direction ="plt_right";
		    	}
		    	if(border_of_dynamic_platforms.get(j).x > dpp_stops.get(j).x)
		    	{
		    		platform_direction ="plt_left";
		    	}
		    	if(platform_direction.equals("plt_right"))
		    	{
		    		dynamic_platformBodies.get(j).setLinearVelocity(sliding_speed,0);		    		
		    	}
		    	if(platform_direction.equals("plt_left"))
		    	{
		    		dynamic_platformBodies.get(j).setLinearVelocity(-1*sliding_speed,0);		    		
		    	}
		    	border_of_dynamic_platforms.get(j).setPosition(dynamic_platformBodies.get(j).getPosition().x - border_of_dynamic_platforms.get(j).width/2 , 
		    			dynamic_platformBodies.get(j).getPosition().y -border_of_dynamic_platforms.get(j).height/4);    		
			}		
		}
			catch(Exception ex)
			{
				System.out.println("hata: " + ex.toString());
			}  
    }
    private void Exit_to_Main_Menu()
    {    	
    		if(pause_m.is_home_button_pressed)
    		{    						
    			my_Dispose();
    			pause_m.DisposeEverything();
    			game.setScreen(game.start_menu);
    			game.dispose();
    		}    	
    }
    private void Bonus_Life()
    {
    	if(in_level_collected_diamonds >0 && in_level_collected_diamonds % 75 == 0)
    		Game_Elements.lives_left +=1;   
    }
    private void Ground_Check()
    {  		
    	
    	ground_type = d_contact_listener.GetSurfaceData();     	
    	
    	if((ground_type.equals("f_ground") || d_contact_listener.dynamic_data.equals("dynamic_platform_exists")) && !is_on_the_edge)
    		is_on_the_edge = false;
    	 
    	if(ground_type.equals("f_triangle_ground"))
    	{    	
    		is_on_the_edge = false;    
    		b2dbody.setGravityScale(0);    	 
    		b2dbody.setLinearDamping(7.5f);  	
    	}
    	if( !d_contact_listener.Check_CharacterOnGround())    		
    	{    		
    		b2dbody.setLinearDamping(0f);
     		b2dbody.setGravityScale(1);  
    	}    	
    	if(different_platform_overlap)
    	{
    		d_animation.stateTime_jumping_left = 0;
    		d_animation.stateTime_jumping_right = 0;
    		ground_type = "f_triangle_ground";
    	} 
    	if(dynamic_platform_overlap)
    	ground_type = "f_dynamic_ground";    	
    
    	///to bring back the normal jump after leaving the triangle platform    
    	int index_of_touched_platform=-1;
    	for (Rectangle r : borders_of_different_shapes) 
    	{
    		if(r.overlaps(feet_box))    		
    			index_of_touched_platform = borders_of_different_shapes.indexOf(r);
    		if(!feet_box.overlaps(r) && body_box.overlaps(r))
    			is_on_the_edge = true;      		
		}    	
    	if(index_of_touched_platform !=-1)
    		different_platform_overlap = true;
    	else 
    		different_platform_overlap = false;       	
    
    	
    	//dynamic_platform_check
    	int dynamic_ground_body_touch_index	= -1;    
    	int dynamic_ground_feet_touch_index = -1;
    	for (Rectangle re : border_of_dynamic_platforms) 
    	{    		
    		 if(re.overlaps(feet_box))
				dynamic_ground_feet_touch_index = border_of_dynamic_platforms.indexOf(re);	
    		 if(re.overlaps(body_box))
    			 dynamic_ground_body_touch_index = border_of_dynamic_platforms.indexOf(re);
		}
    	if(dynamic_ground_feet_touch_index ==-1 && dynamic_ground_body_touch_index !=-1)
    		is_on_the_edge=true;
    	if(dynamic_ground_feet_touch_index !=-1 && dynamic_ground_body_touch_index !=-1 && b2dbody.getLinearVelocity().y == 0)
    		is_on_the_edge = false;
    	if(dynamic_ground_feet_touch_index !=-1 || dynamic_ground_body_touch_index !=-1)
    		on_sliding_platform = true;
    	if(dynamic_ground_feet_touch_index ==-1 && dynamic_ground_body_touch_index ==-1)
    		on_sliding_platform = false;
    	if(dynamic_ground_feet_touch_index !=-1)
    	{
    		overlap_for_friction = true;
    		dynamic_platform_overlap = true;
    	}
    	else
    	{
    		overlap_for_friction = false;
    		dynamic_platform_overlap = false;
    		d_contact_listener.dynamic_data = "no_dynamic_platform_for_you";
    	}    	
    		
    		int index_of_feet_contact = -1;
    		int index_of_body_contact = -1;
    		 for (Rectangle rct : e_floor) 
    		 {
    			 if(feet_box.overlaps(rct))
    				 index_of_feet_contact = e_floor.indexOf(rct);
    			 if(body_box.overlaps(rct))
    				 index_of_body_contact = e_floor.indexOf(rct);
    			 if(index_of_body_contact!=-1 && index_of_feet_contact==-1)   		
    			  	is_on_the_edge = true;    		
			}
			 if(index_of_feet_contact !=-1 && Math.abs(b2dbody.getLinearVelocity().y)==0f)
				 is_on_the_edge=false;
    		 if(index_of_feet_contact!=-1 || dynamic_platform_overlap)
    			 overlap_for_friction = true;
    		 else {
    			 overlap_for_friction = false;	 
    			 }    
    	//Manipulate the friction in runtime, 
        //Also take note that Friction needs to be updated from the contact listener class in the Pre-solve method by the contact object.
    		if(!overlap_for_friction)
    		{	   
    			b2dbody.resetMassData();
    			Array<Fixture> fixtures = b2dbody.getFixtureList();
    			fixtures.get(0).setFriction(0.085f);    		
    			b2dbody.resetMassData();
    		}
    		else
    		{	
    			b2dbody.resetMassData();
    			Array<Fixture> fixtures = b2dbody.getFixtureList();
    			fixtures.get(0).setFriction(1f);
    			b2dbody.resetMassData();
    		}   	
    	//UI.label_messageBox.setText(" Is any hardware button pressed : "  + d_joy_pad.IsAnyHardwareKeyPressed());
    		if(b2dbody.getLinearVelocity().y >2.25f)    		
    			is_on_the_edge = false;   
//    		if(!d_contact_listener.Check_CharacterOnGround() && b2dbody.getLinearVelocity().y ==0 && !dynamic_platform_overlap)
//    			is_on_the_edge = true;    		
    	}
    private void PhysicsFixer()
    {
    	if(b2dbody.getLinearVelocity().y > 15f)
    		b2dbody.setLinearVelocity(new Vector2(b2dbody.getLinearVelocity().x, 0));
    	//System.out.println("y speed:" + b2dbody.getLinearVelocity().y);
    }
    private void TestShapes()
    {
//    	for (Rectangle bar : barreers)
//		{
//			shape_renderer.setAutoShapeType(true);
//			shape_renderer.begin();
//			shape_renderer.setProjectionMatrix(camera.combined);	
//			shape_renderer.setColor(Color.RED);
//			shape_renderer.set(ShapeType.Filled);    		
//			shape_renderer.rect(bar.getX(), bar.getY(), bar.getWidth(),bar.getHeight());
//			shape_renderer.end();	
//		}
    	for(Rectangle br: border_of_dynamic_platforms)
    	{
    		shape_renderer.setAutoShapeType(true);
			shape_renderer.begin();
			shape_renderer.setProjectionMatrix(camera.combined);	
			shape_renderer.setColor(Color.RED);
			shape_renderer.set(ShapeType.Filled);    		
			shape_renderer.rect(br.getX(), br.getY(), br.getWidth(),br.getHeight());
			shape_renderer.end();
    	}
//		for (Enemy e : enemies)
//		{
//			shape_renderer.setAutoShapeType(true);
//			shape_renderer.begin();
//			shape_renderer.setProjectionMatrix(camera.combined);	
//			shape_renderer.setColor(Color.BLUE);
//			shape_renderer.set(ShapeType.Filled);    		
//			shape_renderer.rect(e.border_enemy.getX(),e.border_enemy.getY(), 
//					e.border_enemy.getWidth(),e.border_enemy.getHeight());
//			shape_renderer.end();	
//		}
    	shape_renderer.setAutoShapeType(true);
		shape_renderer.begin();
		shape_renderer.setProjectionMatrix(camera.combined);	
		shape_renderer.setColor(Color.BLUE);
		shape_renderer.rect(feet_box.getX(),feet_box.getY(), 
				feet_box.getWidth(),feet_box.getHeight());
		shape_renderer.end();
		shape_renderer.setAutoShapeType(true);
		shape_renderer.begin();
		shape_renderer.setProjectionMatrix(camera.combined);	
		shape_renderer.setColor(Color.RED);
		shape_renderer.rect(body_box.getX(),body_box.getY(), 
			body_box.getWidth(),body_box.getHeight());
		shape_renderer.end();
		for (Rectangle rct : e_floor) 
		 {
			shape_renderer.setAutoShapeType(true);
			shape_renderer.begin();
			shape_renderer.setProjectionMatrix(camera.combined);	
			shape_renderer.setColor(Color.RED);
			shape_renderer.rect(rct.getX(),rct.getY(), 
				rct.getWidth(),rct.getHeight());
			shape_renderer.end();
		 }
    }
	@Override
	public void render (float delta) 
	{	
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT))
			camera.zoom -=0.01f;
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camera.zoom +=0.01f;
		
		//Background color arrangement...
		if(Game_Elements.Level_data == 6 || Game_Elements.Level_data == 7)
		{			
			//sky blue
			red = 179;green = 240;	blue = 255;			
		}
		if(Game_Elements.Level_data == 9 || Game_Elements.Level_data == 8)
		{
			red = 75; green=212;blue=255;
		}
		
		Gdx.gl.glClearColor(red/255f,green/255f,blue/255f,255/255);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	

	 	batch_character.begin();	
	 	char player_direction='n';
	 	if(b2dbody.getLinearVelocity().x>0)
	 		player_direction = 'r';
	 	if(b2dbody.getLinearVelocity().x<0)
	 		player_direction = 'l';
	 	
    	bg.DrawBackground(batch_character, new Vector2(camera.position.x, camera.position.y), player_direction);
    	batch_character.end();
		
		/// Map events	
		camera.update();
		map_renderer.setView(camera);
		map_renderer.render(); 
		
	     //Box2d action settings		
		 jump_force_character = 114f;
		 character_acceleration = 36.5f;
		 enemy_speed = 5.75f;	
		 enemy_jump_power = 130;		 
		 for(int e=0;e<enemies.size();e++)
		 {
			 enemies.get(e).enemy_speed = 5.75f;
			 enemies.get(e).enemy_jump_power = 130f;			
			 
			 //Keep the enemies' speed constant...
			if(enemies.get(e).enemy_body.getLinearVelocity().x < -enemy_speed)
				enemies.get(e).enemy_body.setLinearVelocity(-enemy_speed,0f);
			if(enemies.get(e).enemy_body.getLinearVelocity().x > enemy_speed)
				enemies.get(e).enemy_body.setLinearVelocity(enemy_speed,0f);	
		 } 
		 
		 world.step(delta*1.325f, 6, 2);
		 
		 //Set the Boss
		Boss_Behaviour(delta);		
		//Kick Counter...
		 if(count_of_kicks >3 )
			 count_of_kicks = 0;
		//Fix character position with b2dbody position...		
		character_position.x = b2dbody.getPosition().x-100/100f;		
  		character_position.y = b2dbody.getPosition().y-100/100f;
  			
  	//CAMERA DYNAMICS			
  		//set the camera _x location...
  		if(character_position.x>camera.viewportWidth/2  && character_position.x>player_start_position.x)
  		camera.position.x = character_position.x+100/100f; 
  		if(character_position.x <=player_start_position.x)
  			camera.position.x = player_start_position.x+100/100f;
  		
  		//set the camera _y location
 	    	camera.position.y = camera.viewportHeight/1.310f;
  	 
  	    ui.show_Level_Health_And_Diamonds(Game_Elements.GetLevelData(),in_level_collected_diamonds);  		
  		Check_End_Of_Level();
  		
  		if(end_of_level)
  		{ 		 
  			Game_Elements.SetLevelData(Game_Elements.GetLevelData() + 1);  		
  			my_Dispose();  			
  			game.setScreen(game.level_menu);	 		
  		}
  		
  		for(int e=0;e<enemies.size();e++)
  		{
  			enemies.get(e).enemy_position.x = enemy_bodies[e].getPosition().x-100/100f;
  			enemies.get(e).enemy_position.y = enemy_bodies[e].getPosition().y-40/100f;
  			enemies.get(e).CheckBareerContact(barreers);
  		}  		
  		
  		Arrange_CheckPoints();  	    
  	    // default values
  	    //feet_box = new Rectangle(body_box.x+24.5f/100f, body_box.y-18/100f,15/100f,15/100f);
  		//body_box = new Rectangle(character_position.x+68/100f,character_position.y+32/100f,63/100f,120/100f);
  		body_box.set(character_position.x+68/100f,character_position.y+32/100f,63/100f,120/100f);
  	    feet_box.set(body_box.x+24.5f/100f, body_box.y-18/100f,15/100f,15/100f);  	    
  	    Arrange_Dynamic_Platforms();
  		
  		// Check functions...
  		Check_Jump_On_Boxes();
  		CheckDamage();
  		Check_Drop_Ground();  			   
  			if(is_player_getting_damage)
  			{
  				b2dbody.setLinearVelocity(new Vector2(0,b2dbody.getLinearVelocity().y));
  				player.PlayerMotion();
  				Enemy_Behaviour();	 			 			
  			}
  			if(!is_player_getting_damage)
  			{
  				Enemy_Behaviour();
  				player.PlayerMotion();  				
  			}  		
  			Draw_Shurikens();
  			if(!Game_Elements.game_paused)
  			CheckShurikenPlayerContact();
  			
  	
  	    //Collect the diamonds... 
  	    Diamond_Behaviour(); 
  	    Place_Hearts();
  	    //Draw the dynamic objects...
  	    Dynamic_Objects_Behaviour();  		
  		//CheckEnemySpeed();  				
        ui.stage_ui.draw();        
        Character_Kick_Controller();      
      
        if(!Game_Elements.game_paused)            
        Game_controller();          
        Check_Player_Trap_Collision();          
       	can_player_jump_on_dob = false;
		char_dob.set(b2dbody.getPosition().x-8/100f, b2dbody.getPosition().y-80/100f, 10/100f, 10/100f);
        Check_Dynamic_Objects_Player_Collusion();        
     //is on pc     
        if(g_controller_type == "JoyPad")
        {
        if(Gdx.app.getType().equals(ApplicationType.Android))
    	   d_joy_pad.Draw_joy_pad(false);   
        	else 
        		d_joy_pad.Draw_joy_pad(true);   
        }
        d_joy_pad.game_controller_camera.update(); 
      
       pause_m.Draw_Pause_Menu();     
       	//Moves Regulator
       	Moves_Regulator();       	
       	//Dynamic box, walking on the enemy and the player collision...
       	Dynamic_Box_Contact_Controller();
    	//Speed limiter for the character
//    	if(Math.abs(b2dbody.getLinearVelocity().x)>4f)
//		{
//			b2dbody.setLinearVelocity(new Vector2(0,b2dbody.getLinearVelocity().y));
//		}
    	Kick_The_Boxes();     
    	ui.Play_Diamond_Animation(); 	
    	ui.Show_Life();
    	if(is_fighting_with_the_boss)
    	{
    		ui.Draw_Boss_Icon();
    		ui.label_vs.setVisible(true);
    	}
    	ui.Draw_Energy_Bar();
    	ui.Draw_Character_Icon();	
    	Gdx.input.setInputProcessor(ip_mx);
    	Exit_to_Main_Menu();    
    	//b2dr.render(world, camera.combined);  	
    	Ground_Check();
    	PlayJumpSfx();
    	PhysicsFixer();
    	//TestShapes();	
	}	
	@Override
	public void resize(int width, int height)
	{
		game_viewport.update(width, height);
		ui.ui_viewport.update(width, height);		
		//d_joy_pad.game_controller_viewport.update(width, height);	
		pause_m.viewport_pause_menu.update(width, height);
		
		device_width  =	width;
		device_height = height;
		d_joy_pad.game_controller_viewport.update(width, height, true);
		 for (Enemy e : enemies) 
		  {
			e.enemy_ui.vp_eui.update(width, height);
		  }
	
	}
	@Override
	public void pause() {
	
		
	}
	@Override
	public void resume() {
		
		
	}
	@Override
	public void hide() {
		
		
	}
	@Override
	public void dispose() {	
		//System.out.println("hello from the gameplay dispose");			
	}
	
	//Player Class 
	public class Player {
		
		public void PlayerMotion()
		{		
			character_position.set(character_position.x,character_position.y - 7/100f);	
			batch_character.enableBlending();
		    batch_character.begin(); 
		    batch_character.setProjectionMatrix(camera.combined);
		if(!player_wins)
		{
			if(on_sliding_platform && g_controller_type.equals("JoyPad") && !is_on_the_edge)	
			{
				jump_force_character  = 95f;
			}
			
			if(is_player_alive)
			{			
				if(!is_player_getting_damage)			
					enable_kick_button= true;					
		    //Jumping here
			PlayerJump();
			if(is_on_the_edge)
	    		Edge_Stand();
			// Walking right here
			PlayerWalkRight();
			//Getting damage from right here
			PlayerGettingDamageFromRight();
			//Kicking right here
			PlayerAttackRight();				
			//Standing right here
			PlayerStandingRight();		
			//Walking Left here
			PlayerWalkLeft();
			//Getting damage from left here
			PlayerGettingDamageFromLeft();			
			//Attack left
			PlayerAttackLeft();
			//Standing Left
			PlayerStandLeft();		
					
			}
			if(!is_player_alive)
			{		
				Player_Losing();		
			}
		 }
			if(player_wins)	
			{
				boss_music.setVolume(0f);
				if(boss_music.isPlaying())
				boss_music.stop();			
				enable_directional_buttons = false;
				enable_kick_button = false;
				enable_jump_button = false;		
				
				time_of_playing_win_animation +=1;
				
				if(time_of_playing_win_animation<=480)
				{
				d_animation.currentFrame_win = d_animation.animation_win.getKeyFrame(d_animation.stateTime_win,false);
			
			
				batch_character.draw(d_animation.currentFrame_win, character_position.x, character_position.y,player_size.x,player_size.y);	

				
					d_animation.stateTime_win +=Gdx.graphics.getDeltaTime();				
					if(d_animation.animation_win.isAnimationFinished(d_animation.stateTime_win) )
					{						
						d_animation.stateTime_win = 0;				
					}		
				}			
				
				if(time_of_playing_win_animation >480)
				{			
									 
					//walk right to the exit for next level
					finished_playing_win_animation = true;
					time_of_playing_win_animation = 482;
					win_music.stop();
					movement="right"; 
					type_of_movement = "dynamic_movement";			
					d_animation.currentFrame_walk_right = d_animation.animation_walk_right.getKeyFrame(d_animation.stateTime_walk_right,false);
				
		
					batch_character.draw(d_animation.currentFrame_walk_right, character_position.x, character_position.y,player_size.x,player_size.y);	
		
					
					d_animation.stateTime_walk_right +=Gdx.graphics.getDeltaTime();				
					if(d_animation.animation_walk_right.isAnimationFinished(d_animation.stateTime_walk_right) )
					{						
						d_animation.stateTime_walk_right = 0;				
					}			
				}
		  }			
			if(finished_playing_win_animation)
			{
				did_finish_the_level = true;
				Game_Elements.check_0_passed = false; Game_Elements.check_1_passed = false;Game_Elements.check_2_passed=false;
				Game_Elements.last_point = new Vector2(5.9f,4.0f);
				b2dbody.setTransform(new Vector2(b2dbody.getPosition().x+7/100f,b2dbody.getPosition().y),0);
				if(body_box.overlaps(enemies.get(7).border_enemy))
				{
					b2dbody.applyLinearImpulse(new Vector2(0,jump_force_character/3), b2dbody.getWorldCenter(), true);
				}
			}		
			batch_character.end();	
			for (Enemy en : enemies)
			{
				if(!en.is_enemy_dead && en.is_enemy_getting_damage)
					PlaySfxPlayerKickingEnemies();
			}
			
		}
	}
	//Player jump method
	private void PlayerJump()
	{
		  //Jumping	
    	if((!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob &&!on_sliding_platform) 
    			&& !is_player_getting_damage && !movement.equals("right_kick")
    			&& !movement.equals("left_kick") && ground_type!="f_triangle_ground" && !is_on_the_edge)
    	{	    	
    		if(movement.equals("right"))
    		{
    			d_animation.currentFrame_jumping_right = d_animation.animation_jumping_right.getKeyFrame(d_animation.stateTime_jumping_right+0.12f,false);      		
                    batch_character.draw(d_animation.currentFrame_jumping_right, character_position.x, character_position.y,player_size.x,player_size.y);	 		
    			d_animation.stateTime_jumping_right +=Gdx.graphics.getDeltaTime();				
    			if(d_animation.animation_jumping_right.isAnimationFinished(d_animation.stateTime_jumping_right))
    			{						
    				d_animation.stateTime_jumping_right = 0;    				
    			}
    		}
    		
    		if(movement.equals("left"))
    		{
    			d_animation.currentFrame_jumping_left = d_animation.animation_jumping_left.getKeyFrame(d_animation.stateTime_jumping_left+0.12f,false);    		
                    batch_character.draw(d_animation.currentFrame_jumping_left, character_position.x, character_position.y,player_size.x,player_size.y);		
    			d_animation.stateTime_jumping_left +=Gdx.graphics.getDeltaTime();				
    			if(d_animation.animation_jumping_left.isAnimationFinished(d_animation.stateTime_jumping_left))
    			{						
    				d_animation.stateTime_jumping_left = 0;	    			
    			}
    		}    	
    	}	   
    	
    	if(ground_type =="f_triangle_ground" && !d_contact_listener.Check_CharacterOnGround() && !is_on_the_edge)
    		Roof_Walk();
    	
	}
	//Player walk right method
	private void PlayerWalkRight()
	{
		// Walking Right
					if(movement == "right" && type_of_movement.equals("dynamic_movement") && !is_player_getting_damage
							&& ( (!d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob 
									&& !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
											&& !can_player_jump_on_dob && !on_sliding_platform)
									|| (d_contact_listener.Check_CharacterOnGround() 
													&& can_player_jump_on_dob)  
									|| (!d_contact_listener.Check_CharacterOnGround() 
											&& !can_player_jump_on_dob && on_sliding_platform) )  && !is_on_the_edge)
					{		
						if(!is_intro_mode)
							d_animation.currentFrame_walk_right = 
							d_animation.animation_walk_right.getKeyFrame(d_animation.stateTime_walk_right,false);
						if(is_intro_mode)
							d_animation.currentFrame_walk_right = 
							d_animation.animation_walk_right.getKeyFrames()[0];
					
						batch_character.draw(d_animation.currentFrame_walk_right, 
								character_position.x, character_position.y,player_size.x,player_size.y);		
						
						d_animation.stateTime_walk_right +=Gdx.graphics.getDeltaTime();				
						if(d_animation.animation_walk_right.isAnimationFinished(d_animation.stateTime_walk_right) )
						{						
							d_animation.stateTime_walk_right = 0;				
						}		
						if(is_intro_mode)
							d_animation.currentFrame_walk_right = d_animation.animation_walk_right.getKeyFrames()[0];
					}
	}
	//Player getting damage from right method
	private void PlayerGettingDamageFromRight()
	{

		if(is_player_getting_damage && damage_direction.equals("right_damage")
				&& ( (!d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob 
						&& !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
								&& !can_player_jump_on_dob && !on_sliding_platform) || (!d_contact_listener.Check_CharacterOnGround() 
										&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
												&& can_player_jump_on_dob) || (!d_contact_listener.Check_CharacterOnGround() 
														&& !can_player_jump_on_dob && on_sliding_platform)) )
		{   
			counter_sound_fx_getting_damage +=1;
			if(counter_sound_fx_getting_damage == 1)
			sound_fx_player_being_hit.play();			
			enable_kick_button = true;
			enable_directional_buttons = false;
			d_animation.currentFrame_getting_impulse_from_right = 
					d_animation.animation_getting_impulse_from_right.getKeyFrame(d_animation.stateTime_getting_impulse_from_right+0.12f,false);
			batch_character.draw(d_animation.currentFrame_getting_impulse_from_right, character_position.x+0.0625f/10f, 
					character_position.y+0.125f/10f,player_size.x*1.0525f,player_size.y*1.0625f);			
			d_animation.stateTime_getting_impulse_from_right +=Gdx.graphics.getDeltaTime();				
			if(d_animation.animation_getting_impulse_from_right.isAnimationFinished(d_animation.stateTime_getting_impulse_from_right))
			{			
				counter_sound_fx_getting_damage = 0 ;	
				d_animation.stateTime_getting_impulse_from_right = 0;
				is_player_getting_damage = false;				
				movement = "left";
				type_of_movement = "static_movement";
				enable_kick_button = true;			
				enable_directional_buttons = true;						
			}		
		}	       
	}
	//Player attack right method
	private void PlayerAttackRight()
	{
		if(movement == "right_kick" && _is_kick_pressed && !is_player_getting_damage
				&& ( (!d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob 
						&& !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
								&& !can_player_jump_on_dob && !on_sliding_platform) || (!d_contact_listener.Check_CharacterOnGround() 
										&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
												&& can_player_jump_on_dob) || (!d_contact_listener.Check_CharacterOnGround()
														&& !can_player_jump_on_dob && on_sliding_platform) ) )
		{					
			PlayerSimpleKickRight();			
			PlayerPunchRight();		
			PlayerAlternativeKickRight();							
		}		
	}
	//Player simple kick right method
	private void PlayerSimpleKickRight()
	{
		if(count_of_kicks==1||count_of_kicks==0)
		{
			kick_wind_counter +=1;					
			if(kick_wind_counter == 1)
				sound_fx_kick_wind.play(0.7f);				
	
			enable_kick_button = false;					
			enable_directional_buttons = false;					
			is_player_getting_damage = false;
	
			d_animation.currentFrame_right_kick = d_animation.animation_right_kick.getKeyFrame(d_animation.stateTime_right_kick + 0.12f,false);
				batch_character.draw(d_animation.currentFrame_right_kick, character_position.x, character_position.y,player_size.x*1.0285f,player_size.y*1.0285f);
				can_enemies_feint = true;		
			d_animation.stateTime_right_kick +=Gdx.graphics.getDeltaTime();					
			
			if(d_animation.animation_right_kick.isAnimationFinished(d_animation.stateTime_right_kick))
			{						
				can_enemies_feint = false;
				kick_wind_counter = 0;
				movement = "right";	
				type_of_movement = "static_movement";
				enable_directional_buttons = true;						
				_is_kick_pressed = false;
				enable_kick_button = true;													
				d_animation.stateTime_right_kick = 0;
			}		
		}			
	}
	//Player punch right method
	private void PlayerPunchRight()
	{
		if(count_of_kicks ==2)
		{
			kick_wind_counter +=1;						
			if(kick_wind_counter == 1)
				sound_fx_kick_wind.play(0.7f);		
			
			enable_kick_button = false;					
			enable_directional_buttons = false;					
			is_player_getting_damage = false;
	
			d_animation.currentFrame_right_punch = d_animation.animation_right_punch .getKeyFrame(d_animation.stateTime_right_punch  + 0.12f,false);		
				batch_character.draw(d_animation.currentFrame_right_punch, character_position.x, character_position.y,player_size.x*1.0182f,player_size.y*1.0182f);				
				can_enemies_feint = true;
		
			d_animation.stateTime_right_punch  +=Gdx.graphics.getDeltaTime();					
			
			if(d_animation.animation_right_punch .isAnimationFinished(d_animation.stateTime_right_punch ))
			{				
				can_enemies_feint = false;
				kick_wind_counter = 0;
				movement = "right";	
				type_of_movement = "static_movement";
				enable_directional_buttons = true;							
				_is_kick_pressed = false;
				enable_kick_button = true;							
				d_animation.stateTime_right_punch  = 0;								
			}		
		}	
	}
	//Player alternative kick rigt method
	private void PlayerAlternativeKickRight()
	{
		if(count_of_kicks ==3 )
		{
			is_flip_kicking = true;
			kick_wind_counter +=1;					
			if(kick_wind_counter == 1)
				sound_fx_kick_wind.play(0.7f);				
	
			enable_kick_button = false;					
			enable_directional_buttons = false;						
			is_player_getting_damage = false;
	
			d_animation.alt_currentFrame_right_kick = d_animation.alt_animation_right_kick.getKeyFrame(d_animation.alt_stateTime_right_kick + 0.12f,false);
			batch_character.draw(d_animation.alt_currentFrame_right_kick, character_position.x, character_position.y,player_size.x*1.0182f,player_size.y*1.0182f);	

			can_enemies_feint = true;
		
			d_animation.alt_stateTime_right_kick +=Gdx.graphics.getDeltaTime();					
			
			if(d_animation.alt_animation_right_kick.isAnimationFinished(d_animation.alt_stateTime_right_kick))
			{			
				can_enemies_feint = false;
				is_flip_kicking = false;
				kick_wind_counter = 0;
				movement = "right";	
				type_of_movement = "static_movement";
				enable_directional_buttons = true;						
				_is_kick_pressed = false;
				enable_kick_button = true;													
				d_animation.alt_stateTime_right_kick =0f;							
			}	
		}
	}
	//Player stand right method
	private void PlayerStandingRight()
	{
		if(movement == "right" && type_of_movement.equals("static_movement") 
				&& ( (!d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob 
						&& !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
								&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
										&& can_player_jump_on_dob)  || (!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob && on_sliding_platform) )
											&& !is_on_the_edge)
		{	
			d_animation.currentFrame_walk_right = d_animation.animation_walk_right.getKeyFrames()[0];
	
			batch_character.draw(d_animation.currentFrame_walk_right, character_position.x, character_position.y,player_size.x,player_size.y);	
			
		}
	}
	//Player walk left method
	private void PlayerWalkLeft()
	{
		if(movement == "left" && type_of_movement.equals("dynamic_movement") 
				&& !is_player_getting_damage && ( (!d_contact_listener.Check_CharacterOnGround() 
						&& can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
								&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
										&& can_player_jump_on_dob) || (!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob && on_sliding_platform)) && !is_on_the_edge)
		{		
			if(!is_intro_mode)
				d_animation.currentFrame_walk_left = d_animation.animation_walk_left.getKeyFrame(d_animation.stateTime_walk_left,false);
			if(is_intro_mode)
				d_animation.currentFrame_walk_left = d_animation.animation_walk_left.getKeyFrames()[0];
		
			batch_character.draw(d_animation.currentFrame_walk_left, character_position.x, character_position.y,player_size.x,player_size.y);	
	
			
			d_animation.stateTime_walk_left +=Gdx.graphics.getDeltaTime();				
			
			if(d_animation.animation_walk_left.isAnimationFinished(d_animation.stateTime_walk_left)  )
			{						
				d_animation.stateTime_walk_left = 0;				
			}		
			
		}
	}
	//Player getting damage from left method
	private void PlayerGettingDamageFromLeft()
	{
		if(is_player_getting_damage && damage_direction.equals("left_damage") 
				&& ( (!d_contact_listener.Check_CharacterOnGround() 
						&& can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
								&& !can_player_jump_on_dob && !on_sliding_platform) || (!d_contact_listener.Check_CharacterOnGround() 
										&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
												&& can_player_jump_on_dob) || (!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob && on_sliding_platform) ) )
		{	
			counter_sound_fx_getting_damage +=1;
			if(counter_sound_fx_getting_damage == 1)
			sound_fx_player_being_hit.play();
			enable_kick_button = true;			
			enable_directional_buttons = false;		
			d_animation.currentFrame_getting_impulse_from_left = d_animation.animation_getting_impulse_from_left.getKeyFrame(d_animation.stateTime_getting_impulse_from_left+0.12f,false);
			batch_character.draw(d_animation.currentFrame_getting_impulse_from_left, character_position.x-1.925f/10f, character_position.y,
						player_size.x*1.0525f,player_size.y*1.0625f);		
		    d_animation.stateTime_getting_impulse_from_left +=Gdx.graphics.getDeltaTime();				
			if(d_animation.animation_getting_impulse_from_left.isAnimationFinished(d_animation.stateTime_getting_impulse_from_left))
			{		
				counter_sound_fx_getting_damage = 0;
				d_animation.stateTime_getting_impulse_from_left = 0;
				is_player_getting_damage = false;			
				movement = "right";
				type_of_movement = "static_movement";				
				enable_kick_button = true;			
				enable_directional_buttons = true;				
		    }		
		}
	}
	//Player attack left method
	private void PlayerAttackLeft()
	{
		if(movement == "left_kick" && _is_kick_pressed
				&& !is_player_getting_damage && ( (!d_contact_listener.Check_CharacterOnGround() 
						&& can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
								&& !can_player_jump_on_dob && !on_sliding_platform) || (!d_contact_listener.Check_CharacterOnGround() 
										&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
												&& can_player_jump_on_dob) || (!d_contact_listener.Check_CharacterOnGround() && !can_player_jump_on_dob && on_sliding_platform)) )
		{					
			// Player Kick Left
			PlayerSimpleKickLeft();
			PlayerPunchLeft();
			PlayerAlternativeKickLeft();
			}					
		}
	private void PlayerPunchLeft()
	{
		if(count_of_kicks==2)
		{
			kick_wind_counter +=1;
			
			if(kick_wind_counter == 1)
				sound_fx_kick_wind.play(0.7f);
			enable_kick_button = false;						
			enable_directional_buttons = false;						
			is_player_getting_damage = false;
	
			d_animation.currentFrame_left_punch = d_animation.animation_left_punch .getKeyFrame(d_animation.stateTime_left_punch  + 0.12f,false);
			batch_character.draw(d_animation.currentFrame_left_punch, character_position.x, character_position.y,player_size.x*1.0182f,player_size.y*1.0182f);	
			can_enemies_feint = true;
			
			d_animation.stateTime_left_punch  +=Gdx.graphics.getDeltaTime();				
			if(d_animation.animation_left_punch .isAnimationFinished(d_animation.stateTime_left_punch ))
			{					
				can_enemies_feint = false;
				kick_wind_counter = 0;
				movement = "left";	
				type_of_movement = "static_movement";
				enable_directional_buttons = true;							
				_is_kick_pressed = false;
				enable_kick_button = true;								
				d_animation.stateTime_left_punch  = 0;								
			}		
		}
	}
	private void PlayerSimpleKickLeft()
	{
		if(count_of_kicks==1||count_of_kicks==0)
		{
			kick_wind_counter +=1;
			if(kick_wind_counter == 1)
				sound_fx_kick_wind.play(0.7f);		
		
			enable_kick_button = false;					
			enable_directional_buttons = false;						
			is_player_getting_damage = false;
		
			d_animation.currentFrame_left_kick = d_animation.animation_left_kick.getKeyFrame(d_animation.stateTime_left_kick+0.12f,false);
			batch_character.draw(d_animation.currentFrame_left_kick, character_position.x, character_position.y,player_size.x*1.0285f,player_size.y*1.0285f);	
			can_enemies_feint = true;		
			d_animation.stateTime_left_kick +=Gdx.graphics.getDeltaTime();				
			if(d_animation.animation_left_kick.isAnimationFinished(d_animation.stateTime_left_kick))
			{				
				can_enemies_feint = false;
				kick_wind_counter = 0;			
				movement = "left";	
				type_of_movement = "static_movement";
				enable_directional_buttons = true;							
				_is_kick_pressed = false;
				enable_kick_button = true;								
			}					
		}				
	}
	private void PlayerAlternativeKickLeft()
	{
		if(count_of_kicks ==3)
		{
			is_flip_kicking = true;
			kick_wind_counter +=1;					
			if(kick_wind_counter == 1)
				sound_fx_kick_wind.play(0.7f);				
	
			enable_kick_button = false;							
			enable_directional_buttons = false;						
			is_player_getting_damage = false;
	
			d_animation.alt_currentFrame_left_kick = d_animation.alt_animation_left_kick.getKeyFrame(d_animation.alt_stateTime_left_kick + 0.12f,false);
	
			batch_character.draw(d_animation.alt_currentFrame_left_kick, character_position.x, character_position.y,player_size.x*1.0182f,player_size.y*1.0182f);	
	
			can_enemies_feint = true;
		
			d_animation.alt_stateTime_left_kick +=Gdx.graphics.getDeltaTime();					
			
			if(d_animation.alt_animation_left_kick.isAnimationFinished(d_animation.alt_stateTime_left_kick))
			{	
				can_enemies_feint = false;
				is_flip_kicking = false;
				kick_wind_counter = 0;
				movement = "left";	
				type_of_movement = "static_movement";
				enable_directional_buttons = true;							
				_is_kick_pressed = false;
				enable_kick_button = true;							
				d_animation.alt_stateTime_left_kick =0f;							
			}	
		}
	}
	//Player stand left method
	private void PlayerStandLeft()
	{
		if(movement == "left" && type_of_movement.equals("static_movement") 
				&& ( (!d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() 
						&& !can_player_jump_on_dob && !on_sliding_platform) || (d_contact_listener.Check_CharacterOnGround() && can_player_jump_on_dob) || (!d_contact_listener.Check_CharacterOnGround()
								&& !can_player_jump_on_dob && on_sliding_platform) ) 
									&& !is_on_the_edge)
		{
			d_animation.currentFrame_walk_left = d_animation.animation_walk_left.getKeyFrames()[0];

			batch_character.draw(d_animation.currentFrame_walk_left, character_position.x, character_position.y,player_size.x,player_size.y);	
		
		}
	}
}
