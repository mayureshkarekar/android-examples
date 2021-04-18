package com.example.jobschedulerdemo;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int JOB_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViewById(R.id.btn_start_job).setOnClickListener(this);
        findViewById(R.id.btn_cancel_job).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_job: {
                scheduleJob();
            }
            break;

            case R.id.btn_cancel_job: {
                cancelJob();
            }
            break;
        }
    }

    private void scheduleJob() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ComponentName sampleJobServiceComponentName = new ComponentName(this, SampleJobService.class);
            JobInfo sampleJobServiceJobInfo = new JobInfo.Builder(JOB_ID, sampleJobServiceComponentName)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setRequiresCharging(false)
                    .setRequiresDeviceIdle(false)
                    .setPersisted(true) // RECEIVE_BOOT_COMPLETED permission required.
                    .setPeriodic(15 * 60 * 1000) // Will repeat in 15 minutes.
                    .build();

            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = jobScheduler.schedule(sampleJobServiceJobInfo);

            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Toast.makeText(this, R.string.job_scheduled_successfully, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.failed_schedule_the_job, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.job_scheduler_not_supported, Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelJob() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

            /* Cancels the specified job. If the job is currently executing, it is stopped
            immediately and the return value from JobService.onStopJob() method is ignored. */
            jobScheduler.cancel(JOB_ID);

            Toast.makeText(this, R.string.job_cancelled, Toast.LENGTH_SHORT).show();
        }
    }
}