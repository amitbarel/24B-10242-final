# GroupedListModule 🛒📊

GroupedListModule is an Android library for creating a grouped list with sorting functionality. This module supports grouping items by category or the first letter of their name, and allows for sorting items within each group by name or price.

## Features

- **Grouping**: Automatically group items by category or the first letter of their name.
- **Sorting**: Sort items within each group by name or price.
- **Custom Headers**: Display headers for each group (category or letter) with customizable text.
- **Flexible Integration**: Easily configurable both programmatically and via XML attributes.

![7TOKgxO](https://github.com/user-attachments/assets/c36d4646-a2bd-4947-9c17-4b5dd384a4d5)

*In the image: Group By_Category and Sort By_Price*

## Installation
To integrate GroupedListModule into your project using JitPack, follow these steps:
1. **Add JitPack Repository**

   Add the JitPack repository to your root `build.gradle` file:

    ```gradle
    allprojects {
        repositories {
            ...
            maven { url =uri("https://jitpack.io") }
        }
    }
    ```

2. **Add Dependency**

   Add the GroupedListModule dependency to your app's `build.gradle` file:

    ```gradle
    dependencies {
        implementation 'com.github.amitbarel:24B-10242-final:1.0.1'
    }
    ```

## Usage

### XML Configuration

Add the `SortedRecyclerView` to your layout XML and set the `sortCriteria` attribute:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <dev.amitb.groupedlistmodule.SortedRecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="30dp"
        app:sortCriteria="by_category" />
</LinearLayout>
```

### Programmatic Configuration

You can also configure the `SortedRecyclerView` programmatically:
```java
SortedRecyclerView recyclerView = findViewById(R.id.recyclerView);
SortedListAdapter adapter = new SortedListAdapter(items);
recyclerView.setAdapter(adapter);
recyclerView.setSortCriteria(SortCriteria.BY_NAME); // or BY_CATEGORY, BY_PRICE
```

## Contributing

Amit Barel
