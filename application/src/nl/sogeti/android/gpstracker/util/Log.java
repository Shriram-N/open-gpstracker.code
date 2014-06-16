/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: rene
 ** Copyright: (c) 16 jun. 2014 Sogeti Nederland B.V. All Rights Reserved.
 **------------------------------------------------------------------------------
 ** Sogeti Nederland B.V.            |  No part of this file may be reproduced  
 ** Distributed Software Engineering |  or transmitted in any form or by any        
 ** Lange Dreef 17                   |  means, electronic or mechanical, for the      
 ** 4131 NJ Vianen                   |  purpose, without the express written    
 ** The Netherlands                  |  permission of the copyright holder.
 *------------------------------------------------------------------------------
 */
package nl.sogeti.android.gpstracker.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import nl.sogeti.android.gpstracker.BuildConfig;
import android.content.Context;
import android.text.TextUtils;

/**
 * Single toggle for log statements
 * 
 * @author rene (c) 16 jun. 2014, Sogeti B.V.
 */
public class Log
{
   public static final boolean DEBUG = BuildConfig.DEBUG;

   private static String getTag(Object tag)
   {
      String tagName = "";
      if (tag instanceof String)
      {
         tagName = (String) tag;
      }
      else
      {
         tagName = tag.getClass().getSimpleName();
         if (TextUtils.isEmpty(tagName))
         {
            tagName = tag.getClass().getCanonicalName();
         }

         if (TextUtils.isEmpty(tagName))
         {
            tagName = tag.getClass().getName();
            if (tagName.contains("."))
            {
               tagName = tagName.substring(tagName.lastIndexOf(".") + 1);
            }
            if (tagName.contains("$"))
            {
               tagName = tagName.substring(0, tagName.indexOf("$"));
            }
         }
      }
      return tagName;
   }

   public static void v(Object tag, String msg)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.v((String) tag, msg);
      }
   }

   public static void d(Object tag, String msg)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.d((String) tag, msg);
      }
   }

   public static void i(Object tag, String msg)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.i((String) tag, msg);
      }
   }

   public static void w(Object tag, String msg)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.w((String) tag, msg);
      }
   }

   public static void w(Object tag, String msg, Throwable e)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.e((String) tag, msg, e);
      }
   }

   public static void e(Object tag, String msg)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.e((String) tag, msg);
      }
   }

   public static void e(Object tag, String msg, Throwable e)
   {
      if (DEBUG)
      {
         tag = getTag(tag);
         android.util.Log.e((String) tag, msg, e);
      }
   }

   /**
    * When DEBUG is set to true formats the calendar into date time string.
    * 
    * @return the date-time text or empty string
    */
   public static String formatData(Context ctx, Calendar cal)
   {
      String dateTime = "";
      if (DEBUG && cal != null)
      {
         DateFormat formatter = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());
         String tz = cal.getTimeZone().getID();
         dateTime = formatter.format(cal.getTime()) + "-" + tz;
      }
      return dateTime;
   }
}