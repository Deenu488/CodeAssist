package com.tyron.builder.compiler;

import com.tyron.builder.compiler.apk.PackageTask;
import com.tyron.builder.compiler.apk.SignTask;
import com.tyron.builder.compiler.firebase.GenerateFirebaseConfigTask;
import com.tyron.builder.compiler.incremental.dex.IncrementalD8Task;
import com.tyron.builder.compiler.incremental.java.IncrementalJavaTask;
import com.tyron.builder.compiler.incremental.kotlin.IncrementalKotlinCompiler;
import com.tyron.builder.compiler.incremental.resource.IncrementalAapt2Task;
import com.tyron.builder.compiler.manifest.ManifestMergeTask;
import com.tyron.builder.compiler.symbol.MergeSymbolsTask;
import com.tyron.builder.log.ILogger;
import com.tyron.builder.project.api.AndroidProject;

import java.util.ArrayList;
import java.util.List;

public class AndroidAppBuilder extends BuilderImpl<AndroidProject> {

    public AndroidAppBuilder(AndroidProject project, ILogger logger) {
        super(project, logger);
    }

    @Override
    public List<Task<? super AndroidProject>> getTasks(BuildType type) {
        List<Task<? super AndroidProject>> tasks = new ArrayList<>();
        tasks.add(new CleanTask(getProject(), getLogger()));
        tasks.add(new ManifestMergeTask(getProject(), getLogger()));
        tasks.add(new GenerateFirebaseConfigTask(getProject(), getLogger()));
        tasks.add(new IncrementalAapt2Task(getProject(), getLogger(), false));
        tasks.add(new MergeSymbolsTask(getProject(), getLogger()));
        tasks.add(new IncrementalKotlinCompiler(getProject(), getLogger()));
        tasks.add(new IncrementalJavaTask(getProject(), getLogger()));
        tasks.add(new IncrementalD8Task(getProject(), getLogger()));
        tasks.add(new PackageTask(getProject(), getLogger()));
        tasks.add(new SignTask(getProject(), getLogger()));
        return tasks;
    }
}