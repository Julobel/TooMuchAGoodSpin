package com.tmags.game.Utils;

public class EnemyDef {
    public String texturePath;
    public Integer textureWidth;
    public Integer textureHeight;
    public Integer hitBoxRadius;

    public EnemyDef(String texturePath, Integer textureWidth, Integer textureHeight, Integer hitBoxRadius) {
        this.texturePath = texturePath;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.hitBoxRadius = hitBoxRadius;
    }
}
