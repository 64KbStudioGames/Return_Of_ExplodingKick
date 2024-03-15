package com.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Contact_Listener_Player implements ContactListener
{
	public boolean is_character_on_the_ground;
	Fixture feet;
	Fixture ground;	
	String surface_data;
	public String dynamic_data;

	public Contact_Listener_Player() 
	{
		is_character_on_the_ground = false;
		surface_data = "n/a";
		dynamic_data = "n/a";
	}
	@Override
	public void beginContact(Contact contact) 
	{
		//if your physical shape is a Circle use fixtureB(), don't ask me why coz i don't know why too :))
		feet = contact.getFixtureA();	
		ground = contact.getFixtureB();		
		
		try {
			if(feet.getUserData() == "character_foot" && (ground.getUserData().equals("f_ground") 
					|| ground.getUserData().equals("f_triangle_ground") || ground.getUserData().equals("f_dynamic_ground")))
			{
				surface_data = ground.getUserData().toString();
				is_character_on_the_ground = true;				
			}
		
		} catch(Exception ex) {}
	}
	@Override
	public void endContact(Contact contact) 
	{
		
		feet = contact.getFixtureA();			
		ground = contact.getFixtureB();	
		try {
			if(feet.getUserData() == "character_foot")
				{					
					surface_data = "n/a";
					dynamic_data = "no_dynamic_platform";
					is_character_on_the_ground = false;					
				}
		}
		catch(Exception ex ) {}
	}	
	public boolean Check_CharacterOnGround()
	{		
		return is_character_on_the_ground;
	}
	public String GetSurfaceData()
	{
		return surface_data;
	}		
	public String GetFeetData()
	{
		return feet.getUserData().toString();
	}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		contact.resetFriction();		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) 
	{
		try 
		{
		if(contact.getFixtureB().getUserData().equals("f_dynamic_ground"))
			dynamic_data = "dynamic_platform_exists";		
		}catch(Exception ex) {}
	}

}
