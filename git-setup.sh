# Replace your_email@example.com with your GitHub email
EMAIL="arshpalwork@gmail.com"

# generate key if it doesn't exist
[ ! -f ~/.ssh/id_ed25519 ] && ssh-keygen -t ed25519 -C "$EMAIL" -f ~/.ssh/id_ed25519 -N ""

# start ssh-agent and add key
eval "$(ssh-agent -s)" && ssh-add ~/.ssh/id_ed25519

# show public key to copy to GitHub
echo "Copy this key to GitHub:"
cat ~/.ssh/id_ed25519.pub

# switch current repo to SSH
git remote set-url origin $(git remote get-url origin | sed -E 's/https:\/\/github.com\//git@github.com:/')
echo "Remote URL updated to SSH:"
git remote -v

