/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package me.wtao.sdk;

public final class R {
    public static final class attr {
        /**  Drawable to use as the icon bitmap. 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static int bitmap=0x7f010005;
        /**  X coordinate of the icon hot spot. 
         <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int hotSpotX=0x7f010006;
        /**  Y coordinate of the icon hot spot. 
         <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int hotSpotY=0x7f010007;
        /**  Reference to a pointer icon drawable with STYLE_ARROW 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static int pointerIconArrow=0x7f010001;
        /**  Reference to a pointer icon drawable with STYLE_SPOT_ANCHOR 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static int pointerIconSpotAnchor=0x7f010004;
        /**  Reference to a pointer icon drawable with STYLE_SPOT_HOVER 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static int pointerIconSpotHover=0x7f010002;
        /**  Reference to a pointer icon drawable with STYLE_SPOT_TOUCH 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static int pointerIconSpotTouch=0x7f010003;
        /**  Reference to the Pointer style 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static int pointerStyle=0x7f010000;
    }
    public static final class drawable {
        public static int ic_launcher=0x7f020000;
        public static int pointer_arrow=0x7f020001;
        public static int pointer_arrow_icon=0x7f020002;
        public static int pointer_spot_anchor=0x7f020003;
        public static int pointer_spot_anchor_icon=0x7f020004;
        public static int pointer_spot_hover=0x7f020005;
        public static int pointer_spot_hover_icon=0x7f020006;
        public static int pointer_spot_touch=0x7f020007;
        public static int pointer_spot_touch_icon=0x7f020008;
    }
    public static final class string {
        public static int app_name=0x7f030000;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 

        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static int AppBaseTheme=0x7f040000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
 Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static int AppTheme=0x7f040001;
    }
    public static final class styleable {
        /** Attributes that can be used with a Pointer.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #Pointer_pointerIconArrow me.wtao.sdk:pointerIconArrow}</code></td><td> Reference to a pointer icon drawable with STYLE_ARROW </td></tr>
           <tr><td><code>{@link #Pointer_pointerIconSpotAnchor me.wtao.sdk:pointerIconSpotAnchor}</code></td><td> Reference to a pointer icon drawable with STYLE_SPOT_ANCHOR </td></tr>
           <tr><td><code>{@link #Pointer_pointerIconSpotHover me.wtao.sdk:pointerIconSpotHover}</code></td><td> Reference to a pointer icon drawable with STYLE_SPOT_HOVER </td></tr>
           <tr><td><code>{@link #Pointer_pointerIconSpotTouch me.wtao.sdk:pointerIconSpotTouch}</code></td><td> Reference to a pointer icon drawable with STYLE_SPOT_TOUCH </td></tr>
           </table>
           @see #Pointer_pointerIconArrow
           @see #Pointer_pointerIconSpotAnchor
           @see #Pointer_pointerIconSpotHover
           @see #Pointer_pointerIconSpotTouch
         */
        public static final int[] Pointer = {
            0x7f010001, 0x7f010002, 0x7f010003, 0x7f010004
        };
        /**
          <p>
          @attr description
           Reference to a pointer icon drawable with STYLE_ARROW 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name android:pointerIconArrow
        */
        public static final int Pointer_pointerIconArrow = 0;
        /**
          <p>
          @attr description
           Reference to a pointer icon drawable with STYLE_SPOT_ANCHOR 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name android:pointerIconSpotAnchor
        */
        public static final int Pointer_pointerIconSpotAnchor = 3;
        /**
          <p>
          @attr description
           Reference to a pointer icon drawable with STYLE_SPOT_HOVER 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name android:pointerIconSpotHover
        */
        public static final int Pointer_pointerIconSpotHover = 1;
        /**
          <p>
          @attr description
           Reference to a pointer icon drawable with STYLE_SPOT_TOUCH 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name android:pointerIconSpotTouch
        */
        public static final int Pointer_pointerIconSpotTouch = 2;
        /** Attributes that can be used with a PointerIcon.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #PointerIcon_bitmap me.wtao.sdk:bitmap}</code></td><td> Drawable to use as the icon bitmap.</td></tr>
           <tr><td><code>{@link #PointerIcon_hotSpotX me.wtao.sdk:hotSpotX}</code></td><td> X coordinate of the icon hot spot.</td></tr>
           <tr><td><code>{@link #PointerIcon_hotSpotY me.wtao.sdk:hotSpotY}</code></td><td> Y coordinate of the icon hot spot.</td></tr>
           </table>
           @see #PointerIcon_bitmap
           @see #PointerIcon_hotSpotX
           @see #PointerIcon_hotSpotY
         */
        public static final int[] PointerIcon = {
            0x7f010005, 0x7f010006, 0x7f010007
        };
        /**
          <p>
          @attr description
           Drawable to use as the icon bitmap. 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name android:bitmap
        */
        public static final int PointerIcon_bitmap = 0;
        /**
          <p>
          @attr description
           X coordinate of the icon hot spot. 


          <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android:hotSpotX
        */
        public static final int PointerIcon_hotSpotX = 1;
        /**
          <p>
          @attr description
           Y coordinate of the icon hot spot. 


          <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android:hotSpotY
        */
        public static final int PointerIcon_hotSpotY = 2;
        /** Attributes that can be used with a Theme.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #Theme_pointerStyle me.wtao.sdk:pointerStyle}</code></td><td> Reference to the Pointer style </td></tr>
           </table>
           @see #Theme_pointerStyle
         */
        public static final int[] Theme = {
            0x7f010000
        };
        /**
          <p>
          @attr description
           Reference to the Pointer style 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name android:pointerStyle
        */
        public static final int Theme_pointerStyle = 0;
    };
}
