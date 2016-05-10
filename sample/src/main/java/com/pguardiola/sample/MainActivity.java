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

package com.pguardiola.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.pguardiola.CustomItemClickListener;
import com.pguardiola.HexAdapter;
import com.pguardiola.SpacesItemDecoration;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final int SPAN_COUNT = 2;
  private RecyclerView tilesView;
  private RecyclerView.Adapter tilesAdapter;
  private GridLayoutManager layoutManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tilesView = (RecyclerView) findViewById(R.id.tiles);
    tilesView.setHasFixedSize(true);

    int squareSideLengthInPixels = getResources().getDimensionPixelSize(R.dimen.square_side_length);
    float radius = squareSideLengthInPixels / 2;
    float adjacent = (float) (Math.sqrt(3) * radius / 2);
    int spacingInPixels = -(Math.round(radius - adjacent));
    tilesView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

    GridLayoutManager.SpanSizeLookup spanSeries = new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return position % 3 == 0 ? 2 : 1;
      }
    };
    layoutManager = new GridLayoutManager(this, SPAN_COUNT, LinearLayoutManager.VERTICAL, false);
    layoutManager.setSpanSizeLookup(spanSeries);

    tilesView.setLayoutManager(layoutManager);

    final List<String> tiles = new ArrayList<String>() { {
      add("8");
      add("5");
      add("3");
      add("4");
      add("2");
      add("11");
      add("6");
      add("9");
      add("12");
      add("10");
      add("6");
      add("8");
      add("9");
      add("5");
      add("11");
      add("4");
      add("10");
      add("3");
    } };
    CustomItemClickListener onClickListener = new CustomItemClickListener() {
      @Override public void onItemClick(View v, int position) {
        Toast.makeText(getApplicationContext(), tiles.get(position), Toast.LENGTH_SHORT).show();
      }
    };
    tilesAdapter = new HexAdapter(tiles, onClickListener);
    tilesView.setAdapter(tilesAdapter);
  }
}
