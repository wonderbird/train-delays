## Deployment

This document describes how to manually deploy the `train-delays` Docker container to [Google Cloud
Run](https://cloud.google.com/run?hl=en).

### Prerequisites

1. [Install the gcloud CLI](https://cloud.google.com/sdk/docs/install?hl=en)

2. Authenticate with gcloud

   ```shell
   gcloud init
   ```

3. Follow the instructions in [Store Docker container images in Artifact
   Registry](https://cloud.google.com/artifact-registry/docs/docker/store-docker-container-images?hl=en) to

    - create a project with billing enabled
    - enable the [Artifact Registry API](https://console.cloud.google.com/apis/library/artifactregistry.googleapis.com) in
      your project.
    - [create a Docker repository](https://cloud.google.com/artifact-registry/docs/docker/store-docker-container-images?hl=en#create)

4. Create a service to automatically build the project by following [Deploy a container to Cloud
      Run](https://cloud.google.com/run/docs/quickstarts/deploy-container?hl=en)

   - When creating the service, connect to your (forked) GitHub repository

### Links and References

- Geekflare: [10 Best Docker Hosting Platforms for your
  Containers](https://geekflare.com/docker-hosting-platforms/#geekflare-toc-google-cloud-run), June 30, 2022.

#### Google Cloud Run Documentation

1. [Cloud Run](https://cloud.google.com/run?hl=en) - Product Overview.
1. [Deploy a container to Cloud Run](https://cloud.google.com/run/docs/quickstarts/deploy-container?hl=en)
1. [Building Containers](https://cloud.google.com/run/docs/building/containers?hl=en)
1. [Artifact Registry](https://cloud.google.com/artifact-registry/docs/overview?hl=en)
1. [Store Docker container images in Artifact Registry](https://cloud.google.com/artifact-registry/docs/docker/store-docker-container-images?hl=en)

#### Google Cloud Utilities

- [Google Cloud Console](https://console.cloud.google.com/home/dashboard) - Dashboard.
- [Install the gcloud CLI](https://cloud.google.com/sdk/docs/install?hl=en)
