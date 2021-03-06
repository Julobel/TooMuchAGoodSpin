/**
 * Created by Jules Aubel on 19/06/19.
 */

package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.tmags.game.GameObjects.Player;
import com.tmags.game.Main;
import com.tmags.game.Managers.GameStateManager;
import com.tmags.game.TooMuchAGoodSpin;

public class GameOverScreen implements Screen {

    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Game Over Magle";
    private int currentItem;
    private String[] menuItems;
    private static GlyphLayout glyphLayout = new GlyphLayout();
    private GameStateManager gsm;
    private Player currentPlayer;

    TooMuchAGoodSpin game;
    int score, highscore;

    public GameOverScreen (TooMuchAGoodSpin game, int score, Player currentPlayer) {
        this.game = game;
        this.score = score;
        this.currentPlayer = currentPlayer;
        game.dispose();
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/game-over.mp3"));
        sound.play(1.0f);
        gsm = new GameStateManager();

        // Recupération du highscore de puis le fichier de sauvegarde
        Preferences prefs = Gdx.app.getPreferences("alcoholicgame");
        this.highscore = prefs.getInteger("highscore", 0);

        // Sauvegarde du nouveau highscore
        if (score > highscore) {
            // Ouverture et écriture du nouveau score dans le fichier
            prefs.putInteger("highscore", score);
            // Sauvegarde
            prefs.flush();
        }

        sb = new SpriteBatch();
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/nervous/Nervous.ttf")
        );

        FreeTypeFontGenerator.FreeTypeFontParameter parameterTitleFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterTitleFont.size = 56;
        parameterTitleFont.magFilter = Texture.TextureFilter.Linear;
        parameterTitleFont.minFilter = Texture.TextureFilter.Linear;
        parameterTitleFont.color = new com.badlogic.gdx.graphics.Color(com.badlogic.gdx.graphics.Color.WHITE);

        titleFont = gen.generateFont(parameterTitleFont);

        FreeTypeFontGenerator.FreeTypeFontParameter parameterFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterFont.size = 40;
        parameterFont.magFilter = Texture.TextureFilter.Linear;
        parameterFont.minFilter = Texture.TextureFilter.Linear;
        parameterFont.color = new com.badlogic.gdx.graphics.Color(com.badlogic.gdx.graphics.Color.WHITE);

        font = gen.generateFont(parameterFont);

        glyphLayout.setText(font, title);

        menuItems = new String[] {
                "Rejouer",
                "Quitter"
        };

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // On clear l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(Main.cam.combined);
        sb.begin();

        // Affichage titre
        titleFont.draw(
            sb,
            title,
            375,
            630
        );



        // Affichage menu
        for (int i = 0; i < menuItems.length; i++) {
            font.setColor(currentItem == i ? Color.RED : Color.WHITE);
            font.draw(
                sb,
                menuItems[i],
                560,
                270 - 80 * i
            );
        }

        font.setColor(Color.WHITE);
        font.draw(
                sb,
                "Score : " + currentPlayer.getScore(),
                520,
                480
        );

        font.draw(
                sb,
                "Highscore : " + highscore,
                480,
                410
        );

        sb.end();
        update(delta);
    }

    public void update(float dt) {
        this.handleInput();
    }

    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(currentItem > 0) {
                currentItem--;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(currentItem < menuItems.length - 1) {
                currentItem++;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            select();
        }
    }

    private void select() {
        switch (currentItem) {
            case 0:
                game.setScreen(new PlayScreen(game));
                break;
            case 1:
                Gdx.app.exit();
                break;
        }
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
        titleFont.dispose();
        font.dispose();
        sb.dispose();
    }
}
