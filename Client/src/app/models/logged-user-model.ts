export class LoggedUser {
  constructor (
    public username: string,
    public roles: string[],
    private _token: string,
    public _expiration: Date,
    public userType: string | undefined
    ) { }

  get token() {
    if (!this._expiration || new Date() > this._expiration) {
      return null;
    }
    return this._token;
  }
}
