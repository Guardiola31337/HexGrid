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
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Hexagon extends RelativeLayout {
  private HexagonViewEastWest hexagonViewEastWest;
  private TextView labelName;

  public Hexagon(Context context, AttributeSet attrs) {
    super(context, attrs);

    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.hexagon, this, true);

    hexagonViewEastWest = (HexagonViewEastWest) findViewById(R.id.east_west);
    labelName = (TextView) findViewById(R.id.label_name);

    RelativeLayout.LayoutParams layoutParams =
        (RelativeLayout.LayoutParams) labelName.getLayoutParams();
    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
    labelName.setLayoutParams(layoutParams);
  }

  public Hexagon(Context context) {
    this(context, null);
  }
}
