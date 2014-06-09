/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: rene
 ** Copyright: (c) 9 jun. 2014 Sogeti Nederland B.V. All Rights Reserved.
 **------------------------------------------------------------------------------
 ** Sogeti Nederland B.V.            |  No part of this file may be reproduced  
 ** Distributed Software Engineering |  or transmitted in any form or by any        
 ** Lange Dreef 17                   |  means, electronic or mechanical, for the      
 ** 4131 NJ Vianen                   |  purpose, without the express written    
 ** The Netherlands                  |  permission of the copyright holder.
 *------------------------------------------------------------------------------
 */
package nl.sogeti.android.gpstracker.util;

import nl.sogeti.android.gpstracker.viewer.LoggerMap;
import android.app.Activity;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

/**
 * Creates backups of tracks to Google Drive
 * 
 * @author rene (c) 9 jun. 2014, Sogeti B.V.
 */
public class DriveBackup implements ConnectionCallbacks, OnConnectionFailedListener
{

   private static final String TAG = "DriveBackup";
   private Activity activity;

   public DriveBackup(LoggerMap activity)
   {
      this.activity = activity;
   }

   @Override
   public void onConnectionFailed(ConnectionResult connectionResult)
   {
      if (connectionResult.hasResolution())
      {
         try
         {
            connectionResult.startResolutionForResult(activity, LoggerMap.RESOLVE_CONNECTION_REQUEST_CODE);
         }
         catch (IntentSender.SendIntentException e)
         {
            // Unable to resolve, message user appropriately
         }
      }
      else
      {
         GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), activity, 0).show();
      }
   }

   @Override
   public void onConnected(Bundle bundle)
   {
      Log.d(TAG, "Start backup");
   }

   @Override
   public void onConnectionSuspended(int arg0)
   {
      Log.d(TAG, "Stop backup");
   }

}
