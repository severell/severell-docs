# IDE Configuration
---
[TOC]

If you plan on using an IDE there are a few configurations that are useful when developing with Severell.

## IntelliJ (2020.2)

This guide is written using `IntelliJ 2020.2` and may differ for different versions

### Importing Severell Project

After you have created a new Severell project you can import into IntelliJ. 

1. From the main menu, select File | Open.

   Alternatively, click Open or Import on the welcome screen.

2. In the dialog that opens, select the `pom.xml` file of the project you want to open.
   Click OK.
   
3. In the dialog that opens, click Open as Project
   
### Create Run Configuration

Once you have imported your Severell project we need to setup a run configuration if we want the IDE to build and run our project for us.

1. From the main menu, select Build | Edit Configurations.

2. Press the `+` to add a new run configuration.

3. Choose Application in the dropdown that appears.

4. Give your run configuration a name and set the main class to `App.java`

5. Scroll down to the `Before Launch` section. 

6. Press the `+` in the Before Launch section and choose `Run Maven Goal` and enter `process-classes`

7. Hit `Ok` and then `Ok` again to close run configurations.

Go ahead and run the project and hit `http://localhost:8009` and make sure its working.