import random
from datetime import datetime, timedelta

import mysql.connector
import requests


def fetch_random_users(n):
    url = f'https://randomuser.me/api/?results={n}&inc=gender,name,email,phone,picture,location'
    response = requests.get(url)
    data = response.json()
    return data['results']


def random_start_date():
    return datetime.now() - timedelta(days=random.randint(1, 365))


mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root",
    database="wemove"
)

myCursor = mydb.cursor()

users = fetch_random_users(500)

insert_INTO_member = "INSERT INTO member (firstname,lastname, email,gender,address,phone,picture,active) VALUES (%s, %s,%s, %s,%s,%s, %s,%s)"
insert_INTO_member_subscription = "INSERT INTO member_subscription (memberid,subscriptionid,startdate,paid) VALUES (%s, %s,%s, %s)"

activeUsers = int(0.9 * len(users))
active_user_indices = set(random.sample(range(len(users)), activeUsers))

yearly_sub = int(0.2 * activeUsers)
monthly_sub = int(0.75 * activeUsers)
daily_sub = int(0.05 * activeUsers)

for i, user in enumerate(users):
    firstname = user["name"]["first"]
    lastname = user["name"]["last"]
    email = user["email"]
    gender = user["gender"]
    address = user["location"]["street"]["name"] + " " + user["location"]["city"]
    phone = user["phone"]
    picture = user["picture"]["medium"]

    active = 1 if i in active_user_indices else 0

    myCursor.execute(insert_INTO_member, (firstname, lastname, email, gender, address, phone, picture, active))
    memberId = myCursor.lastrowid

    if active:
        if yearly_sub > 0:
            subscriptionID = 1
            paid = 300
            yearly_sub -= 1
        elif monthly_sub > 0:
            subscriptionID = 2
            paid = 30
            monthly_sub -= 1
        else:
            subscriptionID = 3
            paid = 7
            daily_sub -= 1

        startDate = random_start_date()
        myCursor.execute(insert_INTO_member_subscription, (memberId, subscriptionID, startDate, paid))

myCursor.close()
mydb.commit()
