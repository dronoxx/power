import RPi.GPIO as GPIO
import sys

DEVICE_PIN=16

GPIO.setmode(GPIO.BCM)
GPIO.setup(DEVICE_PIN, GPIO.OUT)

OPTIONS = {
    "HIGH": GPIO.HIGH,
    "LOW": GPIO.LOW
}

if len(sys.argv) > 1:
    option = OPTIONS[sys.argv[1]]
    GPIO.output(DEVICE_PIN,option)
    GPIO.cleanup()
