/*
 * Copyright (C) 2016 Pablo Guardiola Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pguardiola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class HexagonViewNorthSouth extends View {

  private Path hexagonPath;
  private Path hexagonBorderPath;
  private float radius;
  private float width, height;
  private int maskColor;

  public HexagonViewNorthSouth(Context context) {
    super(context);
    init();
  }

  public HexagonViewNorthSouth(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public HexagonViewNorthSouth(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public void setRadius(float r) {
    this.radius = r;
    calculatePath();
  }

  public void setMaskColor(int color) {
    this.maskColor = color;
    invalidate();
  }

  @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    width = MeasureSpec.getSize(widthMeasureSpec);
    height = MeasureSpec.getSize(heightMeasureSpec);
    radius = height / 2;
    calculatePath();
  }

  @Override public void onDraw(Canvas c) {
    super.onDraw(c);
    c.clipPath(hexagonBorderPath, Region.Op.DIFFERENCE);
    c.drawColor(Color.WHITE);
    c.save();
    c.clipPath(hexagonPath, Region.Op.DIFFERENCE);
    c.drawColor(maskColor);
    c.save();
  }

  private void init() {
    hexagonPath = new Path();
    hexagonBorderPath = new Path();
    maskColor = 0xFFb2c311;
  }

  private void calculatePath() {
    float centerX = width / 2;
    float centerY = height / 2;
    float adjacent = (float) (Math.sqrt(3) * radius / 2);
    float opposite = radius / 2;
    float hypotenuse = radius;

    // North-South
    hexagonPath.moveTo(centerX, centerY + hypotenuse);
    hexagonPath.lineTo(centerX - adjacent, centerY + opposite);
    hexagonPath.lineTo(centerX - adjacent, centerY - opposite);
    hexagonPath.lineTo(centerX, centerY - hypotenuse);
    hexagonPath.lineTo(centerX + adjacent, centerY - opposite);
    hexagonPath.lineTo(centerX + adjacent, centerY + opposite);
    hexagonPath.moveTo(centerX, centerY + hypotenuse);

    float radiusBorder = radius - 5;
    float adjacentBorder = (float) (Math.sqrt(3) * radiusBorder / 2);
    float oppositeBorder = radiusBorder / 2;
    float hypotenuseBorder = radiusBorder;

    // North-South
    hexagonBorderPath.moveTo(centerX, centerY + hypotenuseBorder);
    hexagonBorderPath.lineTo(centerX - adjacentBorder, centerY + oppositeBorder);
    hexagonBorderPath.lineTo(centerX - adjacentBorder, centerY - oppositeBorder);
    hexagonBorderPath.lineTo(centerX, centerY - hypotenuseBorder);
    hexagonBorderPath.lineTo(centerX + adjacentBorder, centerY - oppositeBorder);
    hexagonBorderPath.lineTo(centerX + adjacentBorder, centerY + oppositeBorder);
    hexagonBorderPath.moveTo(centerX, centerY + hypotenuseBorder);

    invalidate();
  }
}
