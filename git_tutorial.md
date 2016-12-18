# Git tutorial

## Getting started:

Navigate to the folder where you want the repository to appear with command line.

git clone https://github.com/eekuurne/wepakalenteri.git


## Create a branch:

git checkout -b branchin-nimi

git branch --set-upstream-to=origin/master branchin-nimi

## Commit changes:

git add .

git commit -m "Tähän commitin kuvaus"


## Push to repository:

git pull

git push --set-upstream origin branchin-nimi


## Change back to master branch:

git checkout master


## Make pull request:

Go to GitHub. Choose branch from the dropdown. Click Pull request etc.
