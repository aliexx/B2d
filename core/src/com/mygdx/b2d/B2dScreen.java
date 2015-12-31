package com.mygdx.b2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.b2d.B2d.PPM;

public class B2dScreen implements Screen {
    private static final short BIT_WALL = 1;
    private static final short BIT_BALL = 2;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;

    public B2dScreen() {
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, B2d.WIDTH / PPM, B2d.HEIGHT / PPM);

        createWall(100, 100);
        createWall(300, 200);
        createWall(600, 300);
        createBall(200, 300);
    }

    private void createWall(float x, float y) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(x / PPM, y / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        shape.setAsBox(150 / PPM, 10 / PPM);
        fdef.shape = shape;
        fdef.friction = 0.9f;
        fdef.filter.categoryBits = BIT_WALL;
        fdef.filter.maskBits = BIT_BALL;
        body.createFixture(fdef).setUserData("wall");
    }

    private void createBall(float x, float y) {
        BodyDef bdef = new BodyDef();
        CircleShape cshape = new CircleShape();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(x / PPM, y / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bdef);

        cshape.setRadius(10 / PPM);
        fdef.shape = cshape;
        fdef.restitution = 0.8f;
        fdef.filter.categoryBits = BIT_BALL;
        fdef.filter.maskBits = BIT_BALL | BIT_WALL;
        body.createFixture(fdef).setUserData("ball");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);
        draw();
    }

    public void update(float dt) {
        if (Gdx.input.justTouched())
            createBall(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        world.step(dt, 6, 2);
    }

    public void draw() {
        Color color = Color.BLACK;
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, b2dCam.combined);
    }

    @Override
    public void resize(int width, int height) {
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
        world.dispose();
        b2dr.dispose();
    }
}
