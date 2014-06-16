package nl.sogeti.android.gpstracker.service.backup;

import nl.sogeti.android.gpstracker.service.backup.DriveBackupService.LocalBinder;
import nl.sogeti.android.gpstracker.util.Log;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class DriveBinder
{

   private boolean bound;
   private Listener listener;
   private ServiceConnection connection;
   private DriveBackupService service;

   public DriveBinder(Listener listener)
   {
      this.listener = listener;
      connection = new BluetoothServiceConnection();
      service = null;
   }

   public void startBind(Context ctx)
   {
      if (!bound)
      {
         Intent intent = new Intent(ctx, DriveBackupService.class);
         ctx.bindService(intent, connection, Context.BIND_AUTO_CREATE);
      }
      else
      {
         Log.w(this, "Can not bind when already bound", new IllegalStateException());
      }
   }

   public void endBind(Context ctx)
   {
      if (bound)
      {
         ctx.unbindService(connection);
         service = null;
         bound = false;
      }
      else
      {
         Log.w(this, "Can not unbind when not bound", new IllegalStateException());
      }
   }

   public DriveBackupService getService()
   {
      return service;
   }

   private class BluetoothServiceConnection implements ServiceConnection
   {

      @Override
      public void onServiceConnected(ComponentName className, IBinder serviceBinder)
      {
         LocalBinder localBinder = (LocalBinder) serviceBinder;
         service = localBinder.getService();
         bound = true;
         listener.didBindService(service);
      }

      @Override
      public void onServiceDisconnected(ComponentName arg0)
      {
         service = null;
         bound = false;
      }
   };

   /**
    * Callback when the async bind is finished
    */
   public interface Listener
   {
      void didBindService(DriveBackupService service);
   }
}
