If the lsof commands didn’t return any output, it suggests that no process is currently using ports 8080 or 8081. However, to ensure a completely clean slate, let's try another approach to check and release those ports.
Step 1: Use ss to Check Ports

Since lsof didn’t return anything, you can use the ss command to check if ports 8080 and 8081 are being used:

bash

sudo ss -tuln | grep :8080
sudo ss -tuln | grep :8081

If you see any output showing that these ports are in use, note the PID (Process ID) and kill the processes using the ports with the following command:

bash

sudo kill -9 <PID>

Step 2: Stop Docker Containers

If you want to ensure no Docker containers are currently running and using these ports, run the following:

bash

docker ps  # List running containers

If you see containers running, stop them:

bash

docker stop $(docker ps -q)  # Stop all running containers
docker rm $(docker ps -a -q)  # Remove all stopped containers

This will free up the ports if any container was using them.
Step 3: Re-run the Docker Container

Now that we have cleared the ports, let’s re-run the Docker container on port 8080:

bash

docker run -d -p 8080:8080 nasa-app-story

Step 4: Check if the Application is Running

Once the container is running, verify the application is accessible:

    Use curl from the terminal:

    bash

    curl http://localhost:8080/status

    Try accessing it through Postman or a browser at http://localhost:8080/status.

If you still face issues, check the Docker logs for more information:

bash

docker logs <container-id>


It seems that the process listening on port 8080 is still not showing its process ID (PID), which is unusual. You can try forcefully releasing the port with fuser, as mentioned earlier. Run the following command:

bash

sudo fuser -k 8080/tcp