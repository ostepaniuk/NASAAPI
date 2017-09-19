package Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadManager implements Runnable{
    private final Runnable task;
    private final Callable<Void> callback;

    public DownloadManager(List<Downloadable> downloadManagers, Callable<Void> callback) {

        this.task = new Runnable() {
            @Override
            public void run() {
                downloadManagers.forEach(downloadManager -> {
                    try {
                        downloadManager.download();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };

        this.callback = callback;
    }

    @Override
    public void run() {
        task.run();

        try {
            callback.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
