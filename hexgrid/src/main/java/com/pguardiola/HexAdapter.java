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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HexAdapter extends RecyclerView.Adapter<HexAdapter.ViewHolder> {

  private List<String> labels;
  private Context context;
  private CustomItemClickListener listener;

  public HexAdapter(List<String> theLabels) {
    this(theLabels, null);
  }

  public HexAdapter(List<String> theLabels, CustomItemClickListener listener) {
    this.labels = theLabels;
    this.listener = listener;
  }

  @Override public HexAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    this.context = parent.getContext();
    Hexagon hexagon = new Hexagon(context);
    final ViewHolder holder = new ViewHolder(hexagon);
    hexagon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (listener != null) {
          listener.onItemClick(v, holder.getAdapterPosition());
        }
      }
    });
    return holder;
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int position) {
    final String labelName = labels.get(position);
    viewHolder.labelName.setText(labelName);
    if (position % 5 == 0) {
      viewHolder.hexagonEastWest.setMaskColor(ContextCompat.getColor(context, R.color.ore));
    } else if (position % 3 == 0) {
      viewHolder.hexagonEastWest.setMaskColor(ContextCompat.getColor(context, R.color.wheat));
    } else if (position % 2 == 0) {
      viewHolder.hexagonEastWest.setMaskColor(ContextCompat.getColor(context, R.color.brick));
    } else {
      viewHolder.hexagonEastWest.setMaskColor(ContextCompat.getColor(context, R.color.wood));
    }
  }

  @Override public int getItemCount() {
    return labels.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public HexagonViewEastWest hexagonEastWest;
    public TextView labelName;

    public ViewHolder(View vh) {
      super(vh);
      hexagonEastWest = (HexagonViewEastWest) vh.findViewById(R.id.east_west);
      labelName = (TextView) vh.findViewById(R.id.label_name);
    }
  }
}
