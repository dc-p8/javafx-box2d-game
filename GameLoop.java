import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer
{
	float timeStep;
	float previousNanoTime;
	float accumulatedTime;

	Game game;
	//Boolean paused;
	

	public GameLoop(Game g)
	{
		timeStep = 1f / 60f;
		previousNanoTime = 0f;
		accumulatedTime = 0f;

		game = g;
		//paused = false;
	}

	@Override
	public void handle(long currentNanoTime)
	{
		//System.out.printf("abc");
		if(previousNanoTime == 0)
		{
			previousNanoTime = currentNanoTime;
			return;
		}

		float gap = (currentNanoTime - previousNanoTime) / 1e9f;
		accumulatedTime += gap;
		previousNanoTime = currentNanoTime;
		
		while (accumulatedTime >= timeStep)
		{
		  	game.proceed(timeStep);
		  	accumulatedTime -= timeStep;
		}
		
		game.render();
	}
	
	@Override
	public void stop()
	{
		//paused = true;
		previousNanoTime  = 0;
		super.stop();
	}
	@Override
	public void start()
	{
		//paused = false;
		super.start();
	}

	/*
	public Boolean isPaused()
	{
		return paused;
	}
	*/
}