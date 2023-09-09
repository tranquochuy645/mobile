ConstraintLayout:

    layout_width and layout_height: Specifies the width and height of the view.
    app:layout_constraintTop_toTopOf, app:layout_constraintBottom_toBottomOf, app:layout_constraintStart_toStartOf, app:layout_constraintEnd_toEndOf: Constraints to define the position of views relative to others.
    app:layout_constraintHorizontal_bias, app:layout_constraintVertical_bias: Bias values for horizontal and vertical positioning.
    app:layout_constraintWidth_percent, app:layout_constraintHeight_percent: Percentage-based sizing.
    app:layout_constraintDimensionRatio: Aspect ratio for views.
    app:layout_goneMarginStart, app:layout_goneMarginEnd: Margins when the view is set to GONE.

RelativeLayout:

    layout_width and layout_height: Specifies the width and height of the view.
    layout_alignParentTop, layout_alignParentBottom, layout_alignParentLeft, layout_alignParentRight: Aligns a view's edge with the parent's edge.
    layout_above, layout_below, layout_toLeftOf, layout_toRightOf: Positions a view relative to another view.
    layout_centerHorizontal, layout_centerVertical: Centers a view horizontally or vertically in the parent.
    layout_centerInParent: Centers a view both horizontally and vertically in the parent.

LinearLayout:

    layout_width and layout_height: Specifies the width and height of the view.
    android:orientation: Defines whether the layout is horizontal (horizontal) or vertical (vertical).
    android:gravity: Defines the gravity of the content within the layout.
    android:layout_weight: Specifies how space is distributed among views when using layout_width or layout_height as 0dp.

FrameLayout:

    layout_width and layout_height: Specifies the width and height of the view.
    android:foreground: Sets a foreground drawable (e.g., for ripple effects or overlays).
    android:layout_gravity: Specifies the gravity of the view within the parent.
    android:layout_margin: Sets the margin around the view.
    android:layout_marginLeft, android:layout_marginTop, android:layout_marginRight, android:layout_marginBottom: Individual margin attributes.
