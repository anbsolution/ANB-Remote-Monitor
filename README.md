# ANB Remote Monitor

Android project with two separate applications:

- **ANB Monitor Host** — `com.anb.monitor`
- **ANB Viewer & Access** — `com.anb.viewer`

## Build

GitHub Actions builds both debug APKs on pushes to `main` or by manual
workflow dispatch. Download successful build artifacts from the Actions run.

## Current status

Version 0.1.0 starter screens and CI build workflow.

Remote screen sharing, camera access, WebRTC, Appwrite authentication,
pairing, authorization rules, and session signaling are not implemented yet.
Camera and screen access must remain explicitly consent-based.

Never commit API secrets or private keys to this repository.
