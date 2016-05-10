HexGrid
=======
[![Build Status](https://travis-ci.org/Guardiola31337/HexGrid.svg?branch=master)](https://travis-ci.org/Guardiola31337/HexGrid)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pguardiola/hexgrid/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pguardiola/hexgrid/badge.svg)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)

Android Open Source library providing an Hexagonal Grid for Android.

Screenshots
-----------

![Demo Screenshot][1]

Usage
-----

As _HexGrid_ is developed on top of `RecyclerView` in order to obtain a Hexagonal Grid, you only have to implement a normal _RecyclerView_ using the custom elements provided (`SpacesItemDecoration`, `HexAdapter` and `CustomItemClickListener`).

1. You have to create a layout file which contains the RecyclerView.

    ```xml
    <android.support.v7.widget.RecyclerView
            android:id="@+id/tiles"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/sea"/>
    ```

2. Calculate `spacingInPixels` (separation between hexagons in a grid), create a new `SpacesItemDecoration` with the spacing previously calculated and add it to the view.

    ```java
    tilesView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    ```

3. Instantiate a `LayoutManager`, establish the number of spans occupied by each item in the adapter (e.g. 1-2-1-...-1-2-1) and set the manager to the view.

    ```java
    layoutManager = new GridLayoutManager(this, SPAN_COUNT, LinearLayoutManager.VERTICAL, false);
    layoutManager.setSpanSizeLookup(spanSeries);
    tilesView.setLayoutManager(layoutManager);
    ```

4. Instantiate a new `HexAdapter` with a `CustomItemClickListener` and set the adapter to the view.

    ```java
    tilesAdapter = new HexAdapter(tiles, onClickListener);
    tilesView.setAdapter(tilesAdapter);
    ```

Add it to your project
----------------------

If you are working with gradle, add the dependency to your build.gradle file:
```groovy
dependencies{
    compile 'com.pguardiola:hexgrid:0.0.4'
}
```
if you are working with maven, do it into your pom.xml
```xml
<dependency>
    <groupId>com.pguardiola</groupId>
    <artifactId>hexgrid</artifactId>
    <version>0.0.4</version>
    <type>aar</type>
</dependency>
```

Do you want to contribute? TODO
-------------------------------

- Calculate the correct spacing and fix `SpacesItemDecoration` to put the hexagon items closely (as a real grid)
- Define custom attributes
- Support multiple hexagon custom views in `HexAdapter.ViewHolder`
- Support background images in hexagon custom views

Feel free to report or add any useful feature to the library.

Developed By
------------

* Pablo Guardiola Sánchez - <guardiola31337@gmail.com>

[![Twitter](https://raw.githubusercontent.com/Guardiola31337/guardiola31337.github.io/master/images/twitter-logo.png)](https://twitter.com/Guardiola31337 "Follow me on Twitter")
[![Linkedin](https://raw.githubusercontent.com/Guardiola31337/guardiola31337.github.io/master/images/linkedin-logo.png)](https://es.linkedin.com/in/pabloguardiola "Add me to Linkedin")

License
-------

    Copyright 2016 Pablo Guardiola Sánchez.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: ./art/screenshot.gif

