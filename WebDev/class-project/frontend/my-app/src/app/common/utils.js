export default function sleep(sleepTime) {
    return new Promise ((resolve) => setTimeout(resolve, sleepTime));
}